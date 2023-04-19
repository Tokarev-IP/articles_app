package com.example.articlesproject.login.data

import android.util.Log
import com.example.articlesproject.login.domain.interfaces.SignInInterface
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
) : PhoneAuthProvider.OnVerificationStateChangedCallbacks(), SignInInterface {

    private val dataFlow: MutableSharedFlow<AuthData> = MutableSharedFlow(extraBufferCapacity = 1)

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        Log.d("MYTAG", "onVerificationCompleted:$credential")
        signInWithPhoneAuthCredential(credential)
    }
    override fun onVerificationFailed(e: FirebaseException) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Log.w("MYTAG", "onVerificationFailed", e)

        when (e) {
            is FirebaseAuthInvalidCredentialsException -> {
                Log.d("MYTAG", "error")
                val a = dataFlow.tryEmit(AuthData.Info("Incorrect mobile number"))
                Log.d("MYTAG", a.toString())
            }
            is FirebaseTooManyRequestsException -> {
                dataFlow.tryEmit(AuthData.Info("Too many requests.Try again later"))
            }
            is FirebaseNetworkException -> {
                dataFlow.tryEmit(AuthData.Info("Network error"))
            }
            else -> {
                dataFlow.tryEmit(AuthData.Info("Error"))
            }
        }
    }
    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        Log.d("MYTAG", "onCodeSent")
        dataFlow.tryEmit(AuthData.CodeWasSent(verificationId, token))
    }

    override fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("MYTAG", "signInWithCredential:success")
                    dataFlow.tryEmit(AuthData.Info("Successfully"))

                    Log.d("MYTAG", task.result.user.toString())
                    Log.d("MYTAG", task.result.credential.toString())
                } else {
                    Log.w("MYTAG", "signInWithCredential:failure", task.exception)

                    when (task.exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            dataFlow.tryEmit(AuthData.Info("Incorrect code"))
                        }
                        is FirebaseNetworkException -> {
                            dataFlow.tryEmit(AuthData.Info("Network error"))
                        }
                        is FirebaseTooManyRequestsException -> {
                            dataFlow.tryEmit(AuthData.Info("Too many requests.Try again later"))
                        }
                        else -> {
                            dataFlow.tryEmit(AuthData.Info("Error"))
                        }
                    }
                }
            }
    }

    override fun getCredential(
        verificationId: String,
        code: String,
    ): PhoneAuthCredential {
        Log.d("MYTAG", "setCredential")
        return PhoneAuthProvider.getCredential(verificationId, code)
    }

    override fun getResponseFlow(): MutableSharedFlow<AuthData> {
        dataFlow.tryEmit(AuthData.Info("info test"))
        return dataFlow
    }

    sealed interface AuthData {
        data class CodeWasSent(
            val verificationId: String,
            val token: PhoneAuthProvider.ForceResendingToken,
        ) : AuthData

        object AutoEnter : AuthData
        data class Info(val info: String) : AuthData
    }
}