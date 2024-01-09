package com.example.articlesproject.login.data

import com.example.articlesproject.login.data.interfaces.SignInInterface
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCredentialRepository @Inject constructor(): SignInInterface {
    override fun getCredential(
        verificationId: String,
        code: String,
    ): PhoneAuthCredential {
        return PhoneAuthProvider.getCredential(verificationId, code)
    }
}