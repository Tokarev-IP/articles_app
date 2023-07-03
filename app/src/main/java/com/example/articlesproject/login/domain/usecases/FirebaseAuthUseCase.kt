package com.example.articlesproject.login.domain.usecases

import com.example.articlesproject.login.domain.interfaces.FirebaseAuthInterface
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthUseCase @Inject constructor(
    private val firebaseAuthInterface: FirebaseAuthInterface
) {

    fun getAuthId(): String? = firebaseAuthInterface.getAuthId()

    fun getUser(): FirebaseUser? = firebaseAuthInterface.getUser()

    fun signOut(): Unit = firebaseAuthInterface.signOut()

    fun signWithCredential(credential: PhoneAuthCredential) =
        firebaseAuthInterface.signWithCredential(credential = credential)
}