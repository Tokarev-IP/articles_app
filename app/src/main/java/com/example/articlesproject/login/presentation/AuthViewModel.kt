package com.example.articlesproject.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articlesproject.login.domain.MyTime
import com.example.articlesproject.login.domain.usecases.FirebaseAuthUseCase
import com.example.articlesproject.login.domain.usecases.GetCodeUseCase
import com.example.articlesproject.login.domain.usecases.SignInCallback
import com.example.articlesproject.login.domain.usecases.SignInUseCase
import com.example.articlesproject.login.domain.usecases.states.FirebaseAuthResponseState
import com.example.articlesproject.login.domain.usecases.states.SignInResponseState
import com.example.articlesproject.login.presentation.states.UiIntentsLogin
import com.example.articlesproject.login.presentation.states.UiStatesLogin
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
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
    private val signInCallback: SignInCallback,
) : ViewModel() {

    private val uiState: MutableStateFlow<UiStatesLogin> = MutableStateFlow(UiStatesLogin.Nothing)
    private val uiStateFlow = uiState.asStateFlow()
    fun getUiStateFlow() = uiStateFlow

    private val timerState: MutableStateFlow<String> = MutableStateFlow("")
    private val timerStateFlow = timerState.asStateFlow()
    fun getTimerStateFlow() = timerStateFlow

    private var verificationId: String = ""
    private var timeJob: Job? = null

    init {
        viewModelScope.launch {
            signInCallback.getUiStateFlow().collect {
                when (it) {
                    is SignInResponseState.SignInWithCode -> {
                        uiState.value = UiStatesLogin.CodeWasSent(it.verificationId, it.token)
                        verificationId = it.verificationId
                    }

                    is SignInResponseState.SignInWithCredential -> {
                        signIn(it.credential)
                    }

                    is SignInResponseState.Error -> {
                        timeJob?.cancel()
                        timerState.value = ""
                        uiState.value = UiStatesLogin.Info(it.error)
                    }
                }
            }
        }
    }

    fun setIntent(intent: UiIntentsLogin) {
        when (intent) {
            is UiIntentsLogin.GetCode -> {
                uiState.value = UiStatesLogin.Loading
                verifyPhone(intent.phoneAuthOptions)
                setTimer(MyTime.TIME_OUT_TIME)
            }

            is UiIntentsLogin.SendCode -> {
                uiState.value = UiStatesLogin.Loading
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
                    is FirebaseAuthResponseState.LoginCompletely -> {
                        uiState.value = UiStatesLogin.Complete
                    }

                    is FirebaseAuthResponseState.LoginError -> {
                        uiState.value = UiStatesLogin.Info(it.error)
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

}