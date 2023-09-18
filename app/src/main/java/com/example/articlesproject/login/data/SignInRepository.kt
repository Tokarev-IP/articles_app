package com.example.articlesproject.login.data

import android.util.Log
import com.example.articlesproject.login.data.interfaces.SignInCallbackInterface
import com.example.articlesproject.login.domain.interfaces.SignInInterface
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInRepository @Inject constructor(private val signInCallbackInterface: SignInCallbackInterface) :
    PhoneAuthProvider.OnVerificationStateChangedCallbacks(), SignInInterface {

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        Log.d("MYTAG", "onVerificationCompleted:$credential")
        signInCallbackInterface.onAuthComplete(credential)
    }

    override fun onVerificationFailed(e: FirebaseException) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Log.w("MYTAG", "onVerificationFailed", e)
        signInCallbackInterface.onAuthFailed(e)
    }

    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        Log.d("MYTAG", "onCodeSent")
        signInCallbackInterface.onAuthCodeWasSent(verificationId, token)
    }

    override fun getCredential(
        verificationId: String,
        code: String,
    ): PhoneAuthCredential {
        Log.d("MYTAG", "setCredential")
        return PhoneAuthProvider.getCredential(verificationId, code)
    }
}