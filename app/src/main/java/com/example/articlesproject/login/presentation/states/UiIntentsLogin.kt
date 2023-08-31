package com.example.articlesproject.login.presentation.states

import com.google.firebase.auth.PhoneAuthOptions

sealed interface UiIntentsLogin {
    data class GetCode(val phoneAuthOptions: PhoneAuthOptions) : UiIntentsLogin
    data class SendCode(val code: String) : UiIntentsLogin
}