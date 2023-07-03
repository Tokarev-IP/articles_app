package com.example.articlesproject.login.data

import com.example.articlesproject.login.domain.interfaces.GetAuthCodeInterface
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAuthCodeRepository @Inject constructor(): GetAuthCodeInterface {

    override fun verifyPhoneNumber(options: PhoneAuthOptions) {
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}