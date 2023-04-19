package com.example.articlesproject.login.domain.interfaces

import com.google.firebase.auth.PhoneAuthOptions

interface GetCodeInterface {
    fun verifyPhoneNumber(options: PhoneAuthOptions)
}