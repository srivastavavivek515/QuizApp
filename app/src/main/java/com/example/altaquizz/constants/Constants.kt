package com.example.altaquizz.constants

object Constants {
    val noOfQuestion = (1..10).map { it.toString() }
    val category = listOf(
        "Sports",
        "Geography",
        "History",
        "Politics",
        "Animals",
        "Vehicle",
        "Art",
        "General Knowledge"
    )
    val categoryMap = hashMapOf(
        "Sports" to 21,
        "Geography" to 22,
        "History" to 23,
        "Politics" to 24,
        "Animals" to 27,
        "Vehicle" to 28,
        "Art" to 25,
        "General Knowledge" to 9,
    )

    val difficulty = listOf("Easy", "Medium", "Hard")
    val type = listOf("Multiple Choice", "True/False")


}