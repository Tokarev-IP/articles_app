package com.example.articlesproject.login.domain.usecases

import com.example.articlesproject.login.domain.interfaces.GetCodeInterface
import com.google.firebase.auth.PhoneAuthOptions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCodeUseCase @Inject constructor(private val getCodeInterface: GetCodeInterface) {

    fun execute(options: PhoneAuthOptions) {
        getCodeInterface.verifyPhoneNumber(options)
    }
}