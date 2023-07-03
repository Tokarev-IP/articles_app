package com.example.articlesproject.login.data

import com.example.articlesproject.login.domain.interfaces.AuthDataFlowInterface
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataFlow @Inject constructor(): AuthDataFlowInterface {

    private val dataFlow: MutableSharedFlow<AuthData> = MutableSharedFlow(extraBufferCapacity = 1)

    override fun getDataFlow() = dataFlow

    override fun setItemInFlow(item: AuthData) {
        dataFlow.tryEmit(item)
    }

    sealed interface AuthData {
        data class CodeWasSent(
            val verificationId: String,
            val token: PhoneAuthProvider.ForceResendingToken,
        ) : AuthData

        data class AutoSignIn(
            val credential: PhoneAuthCredential
        ) : AuthData

        object LoginCompletely : AuthData
        data class Info(val info: String) : AuthData
    }
}