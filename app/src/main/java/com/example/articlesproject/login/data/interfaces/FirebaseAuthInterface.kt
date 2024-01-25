package com.example.articlesproject.login.data.interfaces

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential

interface FirebaseAuthInterface {
    fun getAuthId(): String?
    fun getUser(): FirebaseUser?
    fun signOut(): Unit
    fun signWithCredential(
        credential: PhoneAuthCredential,
        response: (Task<AuthResult>) -> Unit,
    )
}