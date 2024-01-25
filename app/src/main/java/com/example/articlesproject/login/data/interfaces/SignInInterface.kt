package com.example.articlesproject.login.data.interfaces

import com.google.firebase.auth.PhoneAuthCredential

interface SignInInterface {
    fun getCredential(verificationId: String, code: String): PhoneAuthCredential
}