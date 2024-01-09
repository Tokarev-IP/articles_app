package com.example.articlesproject.login.data.interfaces

import com.google.firebase.auth.PhoneAuthOptions

interface GetAuthCodeInterface {
    fun verifyPhoneNumber(options: PhoneAuthOptions)
}