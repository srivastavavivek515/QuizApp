package com.example.altaquizz.viewmodel

import androidx.lifecycle.*
import com.example.altaquizz.quiz.*
import kotlinx.coroutines.flow.*

class HomeViewModel:ViewModel() {
    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState

    fun event(event: EventHomeScreen){
        when(event){
            is EventHomeScreen.SetNoOfQuestion->{
                _homeState.value = homeState.value.copy(noOfQuestion = event.data.toInt())
            }
            is EventHomeScreen.SetCategory->{
                _homeState.value = homeState.value.copy(category = event.data)
            }
            is EventHomeScreen.SetDifficulty->{
                _homeState.value = homeState.value.copy(difficulty = event.data)
            }
            is EventHomeScreen.SetType->{
                _homeState.value = homeState.value.copy(type = event.data)
            }
        }
    }
}