package com.example.articlesproject.data

import android.content.Context
import com.example.articlesproject.domain.AuthInterface
import javax.inject.Inject

class AuthRepository @Inject constructor(context: Context, number: String): AuthInterface {
    override fun getVerificationCode() {
        TODO("Not yet implemented")
    }
}