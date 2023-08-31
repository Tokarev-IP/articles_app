package com.example.articlesproject.login.domain.usecases.states

sealed interface FirebaseAuthResponseState {
    object LoginCompletely: FirebaseAuthResponseState
    data class LoginError(val error: String): FirebaseAuthResponseState
}