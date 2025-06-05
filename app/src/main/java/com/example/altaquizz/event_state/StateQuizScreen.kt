package com.example.altaquizz.quiz

import com.example.altaquizz.data.*

data class StateQuizScreen (
    val isLoading:Boolean = false,
    val quizStateList:List<QuizState> = emptyList(),
    val error:String = "",
    val score:Int = 0
)

data class QuizState(val quiz: Quiz, val shuffledOption:List<String>, val selectedOption:Int = -1)