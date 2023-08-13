package com.example.articlesproject.login.data

import com.example.articlesproject.login.domain.interfaces.FirebaseAuthInterface
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
) : FirebaseAuthInterface {

    override fun getAuthId(): String? = auth.uid

    override fun getUser(): FirebaseUser? = auth.currentUser

    override fun signOut(): Unit = auth.signOut()

    override fun signWithCredential(
        credential: PhoneAuthCredential,
        response: (Task<AuthResult>) -> Unit,
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                response(it)
            }
    }
}