package com.example.altaquizz.common.data

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)