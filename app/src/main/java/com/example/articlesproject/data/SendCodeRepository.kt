package com.example.articlesproject.data

import com.example.articlesproject.MainActivity
import com.example.articlesproject.di.MainActivityScope
import com.example.articlesproject.domain.AuthInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@MainActivityScope
class SendCodeRepository @Inject constructor(
    private val activity: MainActivity,
    private val authCallback: AuthCallback,
    private val auth: FirebaseAuth,
) : AuthInterface {

    override fun getVerificationCode(
        phoneNumber: String,
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)                  // Phone number to verify
            .setTimeout(120L, TimeUnit.SECONDS)    // Timeout and unit
            .setActivity(activity)                        // Activity (for callback binding)
            .setCallbacks(authCallback)                       // OnVerificationStateChangedCallbacks
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}