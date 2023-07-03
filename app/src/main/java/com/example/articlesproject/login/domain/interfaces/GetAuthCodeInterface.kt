package com.example.articlesproject.login.domain.interfaces

import com.google.firebase.auth.PhoneAuthOptions

interface GetAuthCodeInterface {
    fun verifyPhoneNumber(options: PhoneAuthOptions)
}