package com.example.articlesproject.domain

import com.example.articlesproject.data.AuthCallback
import kotlinx.coroutines.flow.MutableStateFlow

interface ResponseInterface {
    fun getResponseFlow(): MutableStateFlow<AuthCallback.AuthData>
}