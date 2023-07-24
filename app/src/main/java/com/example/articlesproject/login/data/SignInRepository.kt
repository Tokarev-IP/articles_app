package com.example.articlesproject.login.data

import android.util.Log
import com.example.articlesproject.login.domain.interfaces.SignInInterface
import com.example.articlesproject.login.domain.usecases.AuthResponseFlowUseCase
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInRepository @Inject constructor(
    private val authResponseFlowUseCase: AuthResponseFlowUseCase,
) : PhoneAuthProvider.OnVerificationStateChangedCallbacks(), SignInInterface {

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        Log.d("MYTAG", "onVerificationCompleted:$credential")
        authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.AutoSignIn(credential))
    }

    override fun onVerificationFailed(e: FirebaseException) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Log.w("MYTAG", "onVerificationFailed", e)

        when (e) {
            is FirebaseAuthInvalidCredentialsException -> {
                Log.d("MYTAG", "error")
                val a =
                    authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.Info("Incorrect mobile number"))
                Log.d("MYTAG", a.toString())
            }
            is FirebaseTooManyRequestsException -> {
                authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.Info("Too many requests.Try again later"))
            }
            is FirebaseNetworkException -> {
                authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.Info("Network error"))
            }
            else -> {
                authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.Info("Error"))
            }
        }
    }

    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        Log.d("MYTAG", "onCodeSent")
        authResponseFlowUseCase.setItemInFlow(AuthResponseFlow.AuthData.CodeWasSent(verificationId, token))
    }

    override fun getCredential(
        verificationId: String,
        code: String,
    ): PhoneAuthCredential {
        Log.d("MYTAG", "setCredential")
        return PhoneAuthProvider.getCredential(verificationId, code)
    }

}