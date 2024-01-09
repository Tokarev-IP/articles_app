package com.example.articlesproject.login.domain.usecases

import com.example.articlesproject.login.data.interfaces.SignInInterface
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUseCase @Inject constructor(private val signInInterface: SignInInterface) {

    fun getCredential(verificationId: String, code: String): PhoneAuthCredential {
        return signInInterface.getCredential(verificationId, code)
    }
}