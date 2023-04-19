package com.example.articlesproject.login.domain.interfaces

import com.example.articlesproject.login.data.AuthRepository
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.MutableSharedFlow

interface SignInInterface {
    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential)
    fun getResponseFlow(): MutableSharedFlow<AuthRepository.AuthData>
    fun getCredential(verificationId: String, code: String): PhoneAuthCredential
}