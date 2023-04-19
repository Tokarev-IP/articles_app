package com.example.articlesproject.login

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.articlesproject.login.data.AuthRepository
import com.example.articlesproject.login.presentation.AuthViewModel
import com.example.articlesproject.login.presentation.composable.MainActivityCompose
import com.example.articlesproject.login.theme.ArticlesProjectTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var authRepository: AuthRepository

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
    }

    @Composable
    fun StartUI() {
        val authViewModel = hiltViewModel<AuthViewModel>()

        Log.d("MYTAG", "current user is ${auth.currentUser}")

        Log.d("MYTAG", "StartUI")

        MainActivityCompose(
            onSendCode = {code: String ->
//                authViewModel.setLoadingState()
                authViewModel.sendVerificationCode(code)
            },
            onReceiveCode = { phoneNumber: String ->
                authViewModel.setLoadingState()
                authViewModel.verifyPhone(createOptions(phoneNumber))
                authViewModel.setTimer(TIME_OUT_TIME)
            },
            authViewModel = authViewModel
        )
    }

    fun closeSoft() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun createOptions(phoneNumber: String): PhoneAuthOptions {
        return PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)                  // Phone number to verify
            .setTimeout(TIME_OUT_TIME, TimeUnit.SECONDS)    // Timeout and unit
            .setActivity(this)                        // Activity (for callback binding)
            .setCallbacks(authRepository)                       // OnVerificationStateChangedCallbacks
            .build()
    }

    private companion object My{
        const val TIME_OUT_TIME = 90L
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ArticlesProjectTheme() {
            StartUI()
        }
    }

}