package com.example.articlesproject.login.domain.usecases

import com.example.articlesproject.login.data.AuthRepository
import com.example.articlesproject.login.domain.interfaces.SignInInterface
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUseCase @Inject constructor(private val signInInterface: SignInInterface) {

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        signInInterface.signInWithPhoneAuthCredential(credential)
    }

    fun getResponseFlow(): MutableSharedFlow<AuthRepository.AuthData> {
        return signInInterface.getResponseFlow()
    }

    fun getCredential(verificationId: String, code: String): PhoneAuthCredential {
        return signInInterface.getCredential(verificationId, code)
    }
}