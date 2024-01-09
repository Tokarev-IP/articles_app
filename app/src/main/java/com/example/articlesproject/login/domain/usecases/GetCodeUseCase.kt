package com.example.articlesproject.login.domain.usecases

import com.example.articlesproject.login.data.interfaces.GetAuthCodeInterface
import com.google.firebase.auth.PhoneAuthOptions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCodeUseCase @Inject constructor(private val getAuthCodeInterface: GetAuthCodeInterface) {

    fun execute(options: PhoneAuthOptions) {
        getAuthCodeInterface.verifyPhoneNumber(options)
    }
}