package com.example.articlesproject.domain

import com.example.articlesproject.data.AuthCallback
import com.example.articlesproject.di.AppScope
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@AppScope
class GetResponseFromAuthUseCase @Inject constructor(
    private val responseInterface: ResponseInterface
    ) {

    fun execute(): MutableStateFlow<AuthCallback.AuthData> {
        return responseInterface.getResponseFlow()
    }
}