package com.example.articlesproject.login.data.interfaces

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

interface SignInCallbackInterface {
    fun onAuthComplete(credential: PhoneAuthCredential)
    fun onAuthFailed(e: FirebaseException)
    fun onAuthCodeWasSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    )
}