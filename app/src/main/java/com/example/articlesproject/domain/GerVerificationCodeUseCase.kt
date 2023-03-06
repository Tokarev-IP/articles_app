package com.example.articlesproject.domain

import javax.inject.Inject

class GerVerificationCodeUseCase @Inject constructor(private val authInterface: AuthInterface) {

    fun execute(){
        authInterface.getVerificationCode()
    }
}