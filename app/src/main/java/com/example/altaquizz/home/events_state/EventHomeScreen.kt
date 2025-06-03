package com.example.altaquizz.home.events_state

sealed class EventHomeScreen {
    data class SetNoOfQuestion(val data:String):EventHomeScreen()
    data class SetCategory(val data: String):EventHomeScreen()
    data class SetDifficulty(val data: String):EventHomeScreen()
    data class SetType(val data: String):EventHomeScreen()
}