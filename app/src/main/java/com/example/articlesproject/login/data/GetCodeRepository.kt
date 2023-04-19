package com.example.articlesproject.login.data

import com.example.articlesproject.login.domain.interfaces.GetCodeInterface
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCodeRepository @Inject constructor(): GetCodeInterface {

    override fun verifyPhoneNumber(options: PhoneAuthOptions){
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}