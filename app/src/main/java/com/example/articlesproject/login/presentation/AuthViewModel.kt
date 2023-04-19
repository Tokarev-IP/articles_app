package com.example.articlesproject.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articlesproject.login.data.AuthRepository
import com.example.articlesproject.login.domain.usecases.GetCodeUseCase
import com.example.articlesproject.login.domain.usecases.SignInUseCase
import com.example.articlesproject.login.presentation.states.UiStates
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getCodeUseCase: GetCodeUseCase,
) : ViewModel() {

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

        viewModelScope.launch {
            Log.d("MYTAG", "launch in VM")
            signInUseCase.getResponseFlow()
                .collect() {
                    Log.d("MYTAG", "collect in VM")
                    when (it) {
                        is AuthRepository.AuthData.Info -> {
                            uiState.value = UiStates.Info(it.info)
                            Log.d("MYTAG", it.info)

                            timeJob?.cancelAndJoin()
                            timerState.value = ""
                        }
                        is AuthRepository.AuthData.CodeWasSent -> {
                            uiState.value =
                                UiStates.CodeWasSent(it.verificationId, it.token)
                            verificationId = it.verificationId

                            Log.d("MYTAG", "VM CodeWasSent")
                        }
                        is AuthRepository.AuthData.AutoEnter -> {}
                    }
                }
        }
    }

    fun sendVerificationCode(code: String) {
        val credential = signInUseCase.getCredential(
            verificationId,
            code,
        )
        signIn(credential)
    }

    fun verifyPhone(options: PhoneAuthOptions) {
        getCodeUseCase.execute(options)
    }

    private fun signIn(credential: PhoneAuthCredential) {
        signInUseCase.signInWithPhoneAuthCredential(credential)
    }

    fun setLoadingState() {
        Log.d("MYTAG", "setLoadingState")
        uiState.value = UiStates.Loading
    }

    fun setTimer(time: Long) {
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
        }
    }
}