package com.example.altaquizz.retrofit

import com.example.altaquizz.data.*

interface QuizRepo {
    suspend fun getAllQuiz(amount:Int,category:Int,difficulty: String,type:String):List<Quiz>
}