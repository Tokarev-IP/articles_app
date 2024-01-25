package com.example.articlesproject.login.data

import com.example.articlesproject.login.data.interfaces.SignInCallbackInterface
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInRepository @Inject constructor(private val signInCallbackInterface: SignInCallbackInterface) :
    PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        signInCallbackInterface.onAuthComplete(credential)
    }

    override fun onVerificationFailed(e: FirebaseException) {
        signInCallbackInterface.onAuthFailed(e)
    }

    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        signInCallbackInterface.onAuthCodeWasSent(verificationId, token)
    }
}