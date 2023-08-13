package com.example.articlesproject.main.presentation

import androidx.lifecycle.ViewModel
import com.example.articlesproject.main.data.data.DishData
import com.example.articlesproject.main.data.data.DishTypeData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateMenuViewModel @Inject constructor(): ViewModel() {

    private val dishTypeData: MutableStateFlow<ArrayList<DishTypeData>> = MutableStateFlow(arrayListOf())
    private val dishTypeFlow = dishTypeData.asStateFlow()

    fun setDishType(type: String){
        dishTypeData.value.add(DishTypeData(type, arrayListOf()))
    }

    fun setDishData(dishData: DishData, index: Int){
//        dishTypeData.value[index].dishesList.
    }
}