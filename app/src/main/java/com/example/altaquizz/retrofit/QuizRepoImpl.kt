package com.example.altaquizz.retrofit

import com.example.altaquizz.common.data.*

class QuizRepoImpl(val quizApi: QuizApi) : QuizRepo {
    override suspend fun getAllQuiz(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizApi.getAllQuiz(amount,category,difficulty,type).results
    }
}