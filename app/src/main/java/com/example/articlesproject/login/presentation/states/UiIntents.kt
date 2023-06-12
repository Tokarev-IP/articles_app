package com.example.articlesproject.login.presentation.states

import com.google.firebase.auth.PhoneAuthOptions

sealed interface UiIntents {
    data class GetCode(val phoneAuthOptions: PhoneAuthOptions) : UiIntents
    data class SendCode(val code: String) : UiIntents
}