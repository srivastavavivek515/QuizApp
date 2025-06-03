package com.example.altaquizz.navigation


const val ARG_KEY_NO_OF_QUESTION = "ak_no_question"
const val ARG_KEY_CATEGORY = "ak_category"
const val ARG_KEY_DIFFICULTY = "ak_difficulty"
const val ARG_KEY_TYPE = "ak_type"
sealed class Routes(val route: String) {
    data object HomeScreen : Routes(route = "home_screen")
    data object QuizScreen:Routes(route = "quiz_screen/{$ARG_KEY_NO_OF_QUESTION}/{$ARG_KEY_CATEGORY}/{$ARG_KEY_DIFFICULTY}/{$ARG_KEY_TYPE}")
    fun passQuizParams(noOfQuestion:Int,category:String,difficulty:String,type:String):String{
        return QuizScreen.route.replace(
            oldValue = "{$ARG_KEY_NO_OF_QUESTION}",
            newValue = noOfQuestion.toString()
        ).replace(
            oldValue = "{$ARG_KEY_CATEGORY}",
            newValue = category
        ).replace(
            oldValue = "{$ARG_KEY_DIFFICULTY}",
            newValue = difficulty
        ).replace(
            oldValue = "{$ARG_KEY_TYPE}",
            newValue = type
        )

    }
}