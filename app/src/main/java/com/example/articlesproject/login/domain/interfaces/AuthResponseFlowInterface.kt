package com.example.articlesproject.login.domain.interfaces

import com.example.articlesproject.login.data.AuthResponseFlow
import kotlinx.coroutines.flow.MutableSharedFlow

interface AuthResponseFlowInterface {

    fun setItemInFlow(item: AuthResponseFlow.AuthData)

    fun getDataFlow(): MutableSharedFlow<AuthResponseFlow.AuthData>
}