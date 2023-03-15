package com.example.articlesproject.data

import android.util.Log
import com.example.articlesproject.MainActivity
import com.example.articlesproject.di.MainActivityScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

@MainActivityScope
class SignInRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val activity: MainActivity,
) {
    fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private fun setCredential(
        verificationId: String,
        code: String,
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
    }
}