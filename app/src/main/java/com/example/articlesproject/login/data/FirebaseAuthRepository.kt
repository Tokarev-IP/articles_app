package com.example.articlesproject.login.data

import android.util.Log
import com.example.articlesproject.login.domain.interfaces.FirebaseAuthInterface
import com.example.articlesproject.login.domain.usecases.AuthResponseFlowUseCase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val authResponseFlowUseCase: AuthResponseFlowUseCase,
) : FirebaseAuthInterface {

    override fun getAuthId(): String? = auth.uid

    override fun getUser(): FirebaseUser? = auth.currentUser

    override fun signOut(): Unit = auth.signOut()

    override fun signWithCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("MYTAG", "signInWithCredential:success")
                    authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.LoginCompletely)
                } else {
                    Log.w("MYTAG", "signInWithCredential:failure", it.exception)

                    when (it.exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.Info("Incorrect code"))
                        }
                        is FirebaseNetworkException -> {
                            authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.Info("Network error"))
                        }
                        is FirebaseTooManyRequestsException -> {
                            authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.Info("Too many requests.Try again later"))
                        }
                        else -> {
                            authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.Info("Error"))
                        }
                    }
                }
            }
    }

}