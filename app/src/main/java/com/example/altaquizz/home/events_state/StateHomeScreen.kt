package com.example.altaquizz.home.events_state

data class StateHomeScreen(
    val noOfQuestion:Int = 10,
    val category: String = "Mythology",
    val difficulty: String = "Easy",
    val type:String = "Multiple Choice"
)
