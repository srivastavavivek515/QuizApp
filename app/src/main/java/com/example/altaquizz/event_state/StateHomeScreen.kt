package com.example.altaquizz.event_state

data class StateHomeScreen(
    val noOfQuestion: Int = 10,
    val category: String = "Sports",
    val difficulty: String = "Easy",
    val type: String = "Multiple Choice"
)
