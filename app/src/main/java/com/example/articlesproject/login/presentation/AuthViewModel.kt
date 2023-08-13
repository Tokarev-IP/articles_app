package com.example.articlesproject.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articlesproject.login.data.interfaces.SignInCallbackInterface
import com.example.articlesproject.login.domain.MyTime
import com.example.articlesproject.login.domain.usecases.FirebaseAuthUseCase
import com.example.articlesproject.login.domain.usecases.FirebaseResponseState
import com.example.articlesproject.login.domain.usecases.GetCodeUseCase
import com.example.articlesproject.login.domain.usecases.SignInUseCase
import com.example.articlesproject.login.presentation.states.UiIntents
import com.example.articlesproject.login.presentation.states.UiStates
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getCodeUseCase: GetCodeUseCase,
    private val firebaseAuthUseCase: FirebaseAuthUseCase,
) : ViewModel(), SignInCallbackInterface {

    private val uiState: MutableStateFlow<UiStates> = MutableStateFlow(UiStates.Nothing)
    private val uiStateFlow = uiState.asStateFlow()
    fun getUiStateFlow() = uiStateFlow

    private val timerState: MutableStateFlow<String> = MutableStateFlow("")
    private val timerStateFlow = timerState.asStateFlow()
    fun getTimerStateFlow() = timerStateFlow

    private var verificationId: String = ""
    private var timeJob: Job? = null

    init {
        Log.d("MYTAG", "viewModel init")
        Log.d("MYTAG", this.toString())

        viewModelScope.launch {
            uiState.collect() {
                Log.d("MYTAG", "$it uiState")
            }
        }
    }

    fun setIntent(intent: UiIntents) {
        when (intent) {
            is UiIntents.GetCode -> {
                uiState.value = UiStates.Loading
                verifyPhone(intent.phoneAuthOptions)
                setTimer(MyTime.TIME_OUT_TIME)
            }

            is UiIntents.SendCode -> {
                uiState.value = UiStates.Loading
                sendVerificationCode(intent.code)
            }
        }
    }

    private fun sendVerificationCode(code: String) {
        signIn(getCredential(verificationId, code))
    }

    private fun getCredential(verificationId: String, code: String): PhoneAuthCredential {
        return signInUseCase.getCredential(verificationId, code)
    }

    private fun verifyPhone(options: PhoneAuthOptions) {
        getCodeUseCase.execute(options)
    }

    private fun signIn(credential: PhoneAuthCredential) {
        firebaseAuthUseCase.signWithCredential(
            credential = credential,
            response = {
                when (it) {
                    is FirebaseResponseState.LoginCompletely -> {
                        uiState.value = UiStates.Complete
                    }

                    is FirebaseResponseState.LoginError -> {
                        uiState.value = UiStates.Info(it.error)
                    }
                }
            }
        )
    }

    private fun setTimer(time: Long) {
        var minutes: Long
        var seconds: Long
        var timer = time

        timeJob = viewModelScope.launch {
            while (timer > 0) {
                timer -= 1

                minutes = timer / 60
                seconds = timer % 60

                when (seconds) {
                    0L -> {
                        timerState.value = "$minutes:00"
                    }

                    in 0..9 -> {
                        timerState.value = "$minutes:0$seconds"
                    }

                    else -> {
                        timerState.value = "$minutes:$seconds"
                    }
                }
                delay(1000L)
            }
            timerState.value = ""
            this.cancel()
        }
    }

    override fun onAuthComplete(credential: PhoneAuthCredential) {
        signIn(credential)
    }

    override fun onAuthFailed(e: FirebaseException) {
        when (e) {
            is FirebaseAuthInvalidCredentialsException -> {
                uiState.value = UiStates.Info("Incorrect mobile number")
            }

            is FirebaseTooManyRequestsException -> {
                uiState.value = UiStates.Info("Too many requests.Try again later")
            }

            is FirebaseNetworkException -> {
                uiState.value = UiStates.Info("Network error")
            }

            else -> {
                uiState.value = UiStates.Info("Error")
            }
        }
    }

    override fun onAuthCodeWasSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        uiState.value = UiStates.CodeWasSent(verificationId, token)
    }
}