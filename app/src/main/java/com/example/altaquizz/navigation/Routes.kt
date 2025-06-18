package com.example.altaquizz.navigation

import kotlinx.serialization.*

@Serializable
data object HomeScreen

@Serializable
data class QuizScreen(
    val noOfQuestion: Int,
    val category: String,
    val difficulty: String,
    val type: String
)

@Serializable
data class ScoreScreen(val score: Int)