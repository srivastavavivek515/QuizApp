package com.example.altaquizz.retrofit

import com.example.altaquizz.common.data.*
import retrofit2.http.*

interface QuizApi {
    @GET("api.php")
   suspend fun getAllQuiz(@Query("amount") amount:Int, @Query("category")category:Int, @Query("difficulty")difficulty:String, @Query("type")type:String):QuizResponse
}