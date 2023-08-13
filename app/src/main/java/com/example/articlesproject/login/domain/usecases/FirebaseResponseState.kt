package com.example.articlesproject.login.domain.usecases

sealed interface FirebaseResponseState {
    object LoginCompletely: FirebaseResponseState
    data class LoginError(val error: String): FirebaseResponseState
}