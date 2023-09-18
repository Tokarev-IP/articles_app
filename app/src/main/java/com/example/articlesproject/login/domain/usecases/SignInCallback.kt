package com.example.articlesproject.login.domain.usecases

import com.example.articlesproject.login.data.interfaces.SignInCallbackInterface
import com.example.articlesproject.login.domain.usecases.states.SignInResponseState
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInCallback @Inject constructor() : SignInCallbackInterface {

    private val uiMutableStateFlow: MutableStateFlow<SignInResponseState> = MutableStateFlow(SignInResponseState.Nothing)
    private val uiStateFlow = uiMutableStateFlow.asStateFlow()

    fun getUiStateFlow() = uiStateFlow

    override fun onAuthComplete(credential: PhoneAuthCredential) {
        uiMutableStateFlow.value = SignInResponseState.SignInWithCredential(credential)
    }

    override fun onAuthFailed(e: FirebaseException) {
        when (e) {
            is FirebaseAuthInvalidCredentialsException -> {
                uiMutableStateFlow.value = SignInResponseState.Error("Incorrect mobile number")
            }

            is FirebaseTooManyRequestsException -> {
                uiMutableStateFlow.value =
                    SignInResponseState.Error("Too many requests.Try again later")
            }

            is FirebaseNetworkException -> {
                uiMutableStateFlow.value = SignInResponseState.Error("Network error")
            }

            else -> {
                uiMutableStateFlow.value = SignInResponseState.Error("Error")
            }
        }
    }

    override fun onAuthCodeWasSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        uiMutableStateFlow.value = SignInResponseState.SignInWithCode(verificationId, token)
    }
}