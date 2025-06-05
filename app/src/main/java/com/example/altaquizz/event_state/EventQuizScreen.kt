package com.example.altaquizz.quiz

sealed class EventQuizScreen {
    data class GetQuiz(val no:Int,val category:Int,val difficulty:String,val type:String) : EventQuizScreen()
    data class SetOptionSelected(val quizStateIndex:Int, val selectedOption:Int) : EventQuizScreen()
}
