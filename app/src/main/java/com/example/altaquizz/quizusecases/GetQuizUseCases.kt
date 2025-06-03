package com.example.altaquizz.quizusecases

import com.example.altaquizz.Resources.*
import com.example.altaquizz.common.data.*
import com.example.altaquizz.retrofit.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class GetQuizUseCases(val quizRepo: QuizRepo) {
    fun getAllQuizzes(amount:Int,category:Int,difficulty:String,type:String):Flow<Resource<List<Quiz>>> = flow{
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = quizRepo.getAllQuiz(amount,category,difficulty,type)))
        }catch (e:Exception){
             emit(Resource.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}