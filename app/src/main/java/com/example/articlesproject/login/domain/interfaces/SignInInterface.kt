package com.example.articlesproject.login.domain.interfaces

import com.google.firebase.auth.PhoneAuthCredential

interface SignInInterface {
    fun getCredential(verificationId: String, code: String): PhoneAuthCredential
}