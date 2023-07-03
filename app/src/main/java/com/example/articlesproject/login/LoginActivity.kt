package com.example.articlesproject.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.articlesproject.login.data.SignInRepository
import com.example.articlesproject.login.domain.MyTime
import com.example.articlesproject.login.presentation.AuthViewModel
import com.example.articlesproject.login.presentation.composable.LoginActivityCompose
import com.example.articlesproject.login.presentation.states.UiIntents
import com.example.articlesproject.login.presentation.states.UiStates
import com.example.articlesproject.main.MainActivity
import com.example.articlesproject.theme.ArticlesProjectTheme
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
    lateinit var signInRepository: SignInRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticlesProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (auth.currentUser != null){
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        StartUI()
                    }
                }
            }
        }
    }

    @Composable
    fun StartUI() {
        val authViewModel = hiltViewModel<AuthViewModel>()

        val state by authViewModel.getUiStateFlow().collectAsState()

        when (state){
            is UiStates.Complete -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        Log.d("MYTAG", "StartUI")

        LoginActivityCompose(
            onSendCode = { code: String ->
                authViewModel.setIntent(UiIntents.SendCode(code))
            },
            onReceiveCode = { phoneNumber: String ->
                authViewModel.setIntent(UiIntents.GetCode(createOptions(phoneNumber)))
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
            .setTimeout(MyTime.TIME_OUT_TIME, TimeUnit.SECONDS)    // Timeout and unit
            .setActivity(this)                        // Activity (for callback binding)
            .setCallbacks(signInRepository)                       // OnVerificationStateChangedCallbacks
            .build()
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ArticlesProjectTheme() {
            StartUI()
        }
    }

}