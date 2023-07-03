package com.example.articlesproject.login.domain.interfaces

import com.example.articlesproject.login.data.AuthDataFlow
import kotlinx.coroutines.flow.MutableSharedFlow

interface AuthDataFlowInterface {

    fun setItemInFlow(item: AuthDataFlow.AuthData)

    fun getDataFlow(): MutableSharedFlow<AuthDataFlow.AuthData>
}