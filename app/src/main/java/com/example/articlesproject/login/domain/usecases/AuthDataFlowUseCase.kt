package com.example.articlesproject.login.domain.usecases

import com.example.articlesproject.login.data.AuthDataFlow
import com.example.articlesproject.login.domain.interfaces.AuthDataFlowInterface
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataFlowUseCase @Inject constructor(private val authDataFlowInterface: AuthDataFlowInterface) {

    fun setItemInFlow(item: AuthDataFlow.AuthData) {
        authDataFlowInterface.setItemInFlow(item)
    }

    fun getDataFlow(): MutableSharedFlow<AuthDataFlow.AuthData> =
        authDataFlowInterface.getDataFlow()
}