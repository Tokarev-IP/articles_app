package com.example.articlesproject.login.domain.usecases

import android.util.Log
import com.example.articlesproject.login.domain.interfaces.FirebaseAuthInterface
import com.example.articlesproject.login.domain.usecases.states.FirebaseAuthResponseState
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
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

    fun signWithCredential(
        credential: PhoneAuthCredential,
        response: (FirebaseAuthResponseState) -> Unit,
    ) =
        firebaseAuthInterface.signWithCredential(
            credential = credential,
            response = {
                if (it.isSuccessful) {
                    Log.d("MYTAG", "signInWithCredential:success")
                    response(FirebaseAuthResponseState.LoginCompletely)
                } else {
                    Log.w("MYTAG", "signInWithCredential:failure", it.exception)
                    when (it.exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            response(FirebaseAuthResponseState.LoginError("Incorrect code"))
                        }
                        is FirebaseNetworkException -> {
                            response(FirebaseAuthResponseState.LoginError("Network error"))
                        }
                        is FirebaseTooManyRequestsException -> {
                            response(FirebaseAuthResponseState.LoginError("Too many requests.Try again later"))
                        }
                        else -> {
                            response(FirebaseAuthResponseState.LoginError("Error"))
                        }
                    }
                }
            }
        )
}