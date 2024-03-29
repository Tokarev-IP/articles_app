package com.example.articlesproject.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.articlesproject.login.presentation.states.UiIntentsLogin
import com.example.articlesproject.login.presentation.states.UiStatesLogin
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
            is UiStatesLogin.Complete -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        LoginActivityCompose(
            onSendCode = { code: String ->
                authViewModel.setIntent(UiIntentsLogin.SendCode(code))
            },
            onReceiveCode = { phoneNumber: String ->
                authViewModel.setIntent(UiIntentsLogin.GetCode(createOptions(phoneNumber)))
            },
            authViewModel = authViewModel
        )
    }

    private fun createOptions(phoneNumber: String): PhoneAuthOptions {
        return PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)                            // Phone number to verify
            .setTimeout(MyTime.TIME_OUT_TIME, TimeUnit.SECONDS)     // Timeout and unit
            .setActivity(this)                                      // Activity (for callback binding)
            .setCallbacks(signInRepository)                         // OnVerificationStateChangedCallbacks
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