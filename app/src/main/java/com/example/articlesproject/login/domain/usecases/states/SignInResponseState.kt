package com.example.articlesproject.login.domain.usecases.states

import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

interface SignInResponseState {
    data class SignInWithCredential(val credential: PhoneAuthCredential) :
        SignInResponseState

    data class SignInWithCode(
        val verificationId: String,
        val token: PhoneAuthProvider.ForceResendingToken
    ) : SignInResponseState

    data class Error(val error: String) : SignInResponseState
    object Nothing: SignInResponseState
}
