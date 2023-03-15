package com.example.articlesproject.data

import android.util.Log
import com.example.articlesproject.di.MainActivityScope
import com.example.articlesproject.domain.ResponseInterface
import com.google.android.gms.auth.api.Auth
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@MainActivityScope
class AuthCallback @Inject constructor(
    private val signInRepository: SignInRepository
) : PhoneAuthProvider.OnVerificationStateChangedCallbacks(), ResponseInterface {

    private val dataFlow: MutableStateFlow<AuthData> = MutableStateFlow(AuthData.Initial)

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        Log.d("TAG", "onVerificationCompleted:$credential")
        signInRepository.signInWithPhoneAuthCredential(credential)
    }

    override fun onVerificationFailed(e: FirebaseException) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Log.w("TAG", "onVerificationFailed", e)

        dataFlow.compareAndSet(AuthData.Error(e), dataFlow.value)

        if (e is FirebaseAuthInvalidCredentialsException) {
            // Invalid request
        } else if (e is FirebaseTooManyRequestsException) {
            // The SMS quota for the project has been exceeded
        } else if (e is FirebaseNetworkException) {

        }

        // Show a message and update the UI
    }

    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
    }

    sealed interface AuthData{
        object Initial: AuthData
        object Loading: AuthData
        object CodeWasSent: AuthData
        data class Error(val e:FirebaseException): AuthData
    }

    override fun getResponseFlow(): MutableStateFlow<AuthData> {
        return dataFlow
    }
}