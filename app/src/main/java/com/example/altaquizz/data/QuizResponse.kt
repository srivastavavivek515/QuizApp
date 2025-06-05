package com.example.altaquizz.data

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)