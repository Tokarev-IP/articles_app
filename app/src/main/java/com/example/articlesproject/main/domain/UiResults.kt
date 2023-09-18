package com.example.articlesproject.main.domain

sealed class UiResults {
    object Successful : UiResults()
    data class Error(val e: Exception) : UiResults()
}
