package com.example.articlesproject.login.domain.usecases

import com.example.articlesproject.login.data.AuthResponseFlow
import com.example.articlesproject.login.domain.interfaces.AuthResponseFlowInterface
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthResponseFlowUseCase @Inject constructor(private val authResponseFlowInterface: AuthResponseFlowInterface) {

    fun setItemInFlow(item: AuthResponseFlow.AuthData) {
        authResponseFlowInterface.setItemInFlow(item)
    }

    fun getDataFlow(): MutableSharedFlow<AuthResponseFlow.AuthData> =
        authResponseFlowInterface.getDataFlow()
}