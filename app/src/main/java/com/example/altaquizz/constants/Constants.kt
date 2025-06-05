package com.example.altaquizz.constants

object Constants {
    val noOfQuestion = (1..10).map { it.toString() }
    val category = listOf("Mythology","Sports","History","Geography","Science","Animals","Vehicle","Nature","Entertainment")
    val categoryMap = hashMapOf(
        "Mythology" to 20,
        "Sports" to 21,
        "History" to 22,
        "Geography" to 23,
        "Science" to 24,
        "Animals" to 25,
        "Vehicle" to 26,
        "Nature" to 27,
        "Entertainment" to 28,
    )

    val difficulty = listOf("Easy","Medium","Hard")
    val type = listOf("Multiple Choice","True/False")


}