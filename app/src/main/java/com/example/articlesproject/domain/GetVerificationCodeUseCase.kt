package com.example.articlesproject.domain

import com.example.articlesproject.di.AppScope
import com.example.articlesproject.di.MainActivityScope
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

@AppScope
class GetVerificationCodeUseCase @Inject constructor(
    private val authInterface: AuthInterface
) {
    fun execute(
        phoneNumber: String,
    ) {
        authInterface.getVerificationCode(phoneNumber)
    }
}