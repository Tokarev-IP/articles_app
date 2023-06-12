package com.example.articlesproject.main

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.articlesproject.login.presentation.states.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    init {
        Log.d("MYTAG_MAINVM", this.toString())
    }

    lateinit var uri: Uri
    private val uriState: MutableStateFlow<Uri?> = MutableStateFlow(null)
    private val uriStateFlow = uriState.asStateFlow()

//    private var imageUri: LiveData<Uri> = MutableLiveData()

//    private val uiFlow: MutableStateFlow<UiStates> = MutableStateFlow(UiStates.InitialState)
//    val uiFlowState = uiFlow.asStateFlow()

    fun getImageUriFlow() = uriStateFlow

    fun setImageUri(uri: Uri){
        this.uri = uri
        uriState.value = uri
        Log.d("PhotoPicker", "Selected URI VM: $uri")
    }

}