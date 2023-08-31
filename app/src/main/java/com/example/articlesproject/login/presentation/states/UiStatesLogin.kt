package com.example.articlesproject.login.presentation.states

import com.google.firebase.auth.PhoneAuthProvider

interface UiStatesLogin {
    data class CodeWasSent(
        val verificationId: String,
        val token: PhoneAuthProvider.ForceResendingToken,
    ) : UiStatesLogin
    data class Info(val info: String) : UiStatesLogin
    object Loading: UiStatesLogin
    object Nothing: UiStatesLogin
    object Complete: UiStatesLogin
}