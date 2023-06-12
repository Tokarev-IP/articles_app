package com.example.articlesproject.login.presentation.states

import com.google.firebase.auth.PhoneAuthProvider

interface UiStates {
    data class CodeWasSent(
        val verificationId: String,
        val token: PhoneAuthProvider.ForceResendingToken,
    ) : UiStates
    data class Info(val info: String) : UiStates
    object Loading: UiStates
    object Nothing: UiStates
    object Complete: UiStates
}