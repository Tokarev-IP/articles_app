package com.example.articlesproject.login.data

import android.util.Log
import com.example.articlesproject.login.domain.interfaces.FirebaseAuthInterface
import com.example.articlesproject.login.domain.usecases.AuthDataFlowUseCase
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
    private val authDataFlowUseCase: AuthDataFlowUseCase,
) : FirebaseAuthInterface {

    override fun getAuthId(): String? = auth.uid

    override fun getUser(): FirebaseUser? = auth.currentUser

    override fun signOut(): Unit = auth.signOut()

    override fun signWithCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("MYTAG", "signInWithCredential:success")
                    authDataFlowUseCase.setItemInFlow(AuthDataFlow.AuthData.LoginCompletely)
                } else {
                    Log.w("MYTAG", "signInWithCredential:failure", task.exception)

                    when (task.exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            authDataFlowUseCase.setItemInFlow(AuthDataFlow.AuthData.Info("Incorrect code"))
                        }
                        is FirebaseNetworkException -> {
                            authDataFlowUseCase.setItemInFlow(AuthDataFlow.AuthData.Info("Network error"))
                        }
                        is FirebaseTooManyRequestsException -> {
                            authDataFlowUseCase.setItemInFlow(AuthDataFlow.AuthData.Info("Too many requests.Try again later"))
                        }
                        else -> {
                            authDataFlowUseCase.setItemInFlow(AuthDataFlow.AuthData.Info("Error"))
                        }
                    }
                }
            }
    }

}