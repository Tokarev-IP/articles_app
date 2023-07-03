package com.example.articlesproject.login.domain.interfaces

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential

interface FirebaseAuthInterface {

    fun getAuthId(): String?

    fun getUser(): FirebaseUser?

    fun signOut(): Unit

    fun signWithCredential(credential: PhoneAuthCredential)
}