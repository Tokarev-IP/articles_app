package com.example.articlesproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articlesproject.data.AuthCallback
import com.example.articlesproject.di.AppScope
import com.example.articlesproject.domain.GetResponseFromAuthUseCase
import com.example.articlesproject.domain.GetVerificationCodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AppScope
class AuthViewModel @Inject constructor(
    private val getVerificationCodeUseCase: GetVerificationCodeUseCase,
    private val getResponseFromAuthUseCase: GetResponseFromAuthUseCase,
) : ViewModel() {

    private val uiFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val uiStateFlow = uiFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getResponseFromAuthUseCase.execute()
                .collect {
                    when (it) {
                        is AuthCallback.AuthData.Error -> {}
                        is AuthCallback.AuthData.Loading -> {}
                        is AuthCallback.AuthData.Initial -> {}
                        is AuthCallback.AuthData.CodeWasSent -> {}
                    }
                }
        }
    }

    fun getVerificationCode(
        phoneNumber: String,
    ) {
        getVerificationCodeUseCase.execute(phoneNumber)
    }
}