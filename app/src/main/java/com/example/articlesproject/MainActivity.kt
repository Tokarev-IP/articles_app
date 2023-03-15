package com.example.articlesproject

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.articlesproject.di.ViewModelFactory
import com.example.articlesproject.domain.GetVerificationCodeUseCase
import com.example.articlesproject.presentation.AuthViewModel
import com.example.articlesproject.presentation.composable.MainActivityCompose
import com.example.articlesproject.ui.theme.ArticlesProjectTheme
import com.google.firebase.auth.PhoneAuthOptions
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticlesProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartUI()
                }
            }
        }
        getComponent().inject(this)
        getComponent().getMainActivityComponent().create(this).inject(this)
//
        val viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StartUI(
        modifier: Modifier = Modifier,
//        authViewModel: AuthViewModel = viewModel(),
    ) {
//        val state by authViewModel.uiStateFlow.collectAsState()

        val actionState = rememberSaveable { mutableStateOf("") }

        MainActivityCompose(
            onSendCode = {}
        )

    }

    fun closeSoft() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ArticlesProjectTheme() {
            StartUI()
        }
    }

}