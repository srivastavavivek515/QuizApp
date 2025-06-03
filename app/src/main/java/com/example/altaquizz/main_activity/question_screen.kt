package com.example.altaquizz.main_activity

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.altaquizz.home.components.*
import com.example.altaquizz.quiz.event_state.*

@Composable
fun QuestionScreen(questionNo:Int,quizState:QuizState,onOptionSelected:(Int)->Unit) {
    val questionHashMap = hashMapOf(
        "A" to quizState.shuffledOption[0],
        "B" to quizState.shuffledOption[1],
        "C" to quizState.shuffledOption[2],
        "D" to quizState.shuffledOption[3]
    )

    Column(Modifier.verticalScroll(rememberScrollState())) {

        Row {
            Text(text = "$questionNo", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = quizState.quiz.question, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(10.dp))
        questionHashMap.onEachIndexed { index, (optionName, optionText) ->
            QuizOption(optionName = optionName, optionText = optionText, selected = quizState.selectedOption == index, onOptionClick = {
                   onOptionSelected(index)
            }, onUnSelectOption = {
                    onOptionSelected(-1)
            })
    }
    }

}

@Preview
@Composable
fun ShimmerQuestionScreen() {
    val questionHashMap = hashMapOf(
        "A" to "option 1",
        "B" to "option 2",
        "C" to "Option 3",
        "D" to "Option 4"
    )

    Column(Modifier.verticalScroll(rememberScrollState())) {

        Row {
            Box(modifier = Modifier.size(20.dp).shimmerEffect())
            Spacer(modifier = Modifier.width(10.dp))
            Box(modifier = Modifier.width(100.dp).height(20.dp).shimmerEffect())
        }
        Spacer(modifier = Modifier.height(10.dp))
        questionHashMap.onEachIndexed { index, (optionName, optionText) ->
            QuizOptionShimmer(optionNo = optionName, options = optionText, selected = false, onOptionClick = { /*TODO*/ }) {

            }
        }
    }

}