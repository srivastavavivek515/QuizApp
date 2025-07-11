package com.example.altaquizz.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import com.example.altaquizz.R
import com.example.altaquizz.components.*
import com.example.altaquizz.constants.*
import com.example.altaquizz.event_state.*
import com.example.altaquizz.navigation.*
import kotlinx.coroutines.*


@Composable
fun QuizScreen(
    navHostController: NavHostController,
    noOfQuiz: Int,
    category: String,
    quizDifficulty: String,
    type: String,
    state: StateQuizScreen,
    eventQuizScreen: (EventQuizScreen) -> Unit
) {
    val type = when (type) {
        "Multiple Choice" -> "multiple"
        else -> "boolean"
    }
    LaunchedEffect(key1 = Unit) {
        eventQuizScreen(
            EventQuizScreen.GetQuiz(
                noOfQuiz,
                Constants.categoryMap.get(category)!!,
                quizDifficulty.lowercase(),
                type
            )
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {

        QuizAppBar(category = category) {
            navHostController.popBackStack()
        }

        Column(
            Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            if (isQuizFetched(state = state)) {
                val pagerState = rememberPagerState {
                    state.quizStateList.size
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Questions: $noOfQuiz", style = TextStyle(color = Color.White))
                    Text(text = quizDifficulty, style = TextStyle(color = Color.White))

                }

                Box {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(1.0f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(color = Color.White)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth((pagerState.currentPage.toFloat()/10).toFloat())
                            .height(10.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(color = Color.Green)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))

                HorizontalPager(state = pagerState, modifier = Modifier.weight(0.7f)) { index ->
                    QuestionScreen(
                        index + 1,
                        state.quizStateList[index],
                        onOptionSelected = { selectedIndex ->
                            eventQuizScreen(EventQuizScreen.SetOptionSelected(index, selectedIndex))
                        })
                }
                Spacer(modifier = Modifier.height(50.dp))

                val buttonText by remember {
                    derivedStateOf {
                        when (pagerState.currentPage) {
                            0 -> {
                                listOf("", "Next")
                            }

                            state.quizStateList.size - 1 -> {
                                listOf("Previous", "Submit")
                            }

                            else -> {
                                listOf("Previous", "Next")
                            }
                        }
                    }
                }
                val scope = rememberCoroutineScope()
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.weight(0.2f)
                ) {
                    if (buttonText[0].isNotEmpty()) {
                        RoundedCornerButton(
                            modifier = Modifier.weight(0.4f),
                            text = buttonText[0],
                            textColor = colorResource(id = R.color.black),
                            containerColor = Color.White
                        ) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    RoundedCornerButton(
                        modifier = Modifier.weight(0.4f),
                        text = buttonText[1],
                        textColor = Color.White,
                        containerColor = colorResource(id = R.color.iconColor)
                    ) {
                        scope.launch {
                            if (pagerState.currentPage == state.quizStateList.size - 1) {
                                navHostController.navigate(ScoreScreen(state.score))
                            } else {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)

                            }
                        }
                    }
                }
            }

        }


    }
}

@Composable
fun isQuizFetched(state: StateQuizScreen): Boolean {
    return when {
        state.isLoading -> {
            ShimmerQuestionScreen()
            false
        }
        state.error.isNotEmpty() ->{
            Text(text = state.error.toString())
            false
        }
        state.quizStateList.isNotEmpty() -> {
            true
        }
        else ->{
            NoDataScreen()
            false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizAppBar(category: String, onBackClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.headerColor),
            actionIconContentColor = colorResource(id = R.color.white),
            navigationIconContentColor = colorResource(id = R.color.white)
        ),
        title = {
            Text(
                text = category,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(color = Color.White)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "back"
                )

            }
        }
    )

}


@Composable
fun QuestionScreen(questionNo: Int, quizState: QuizState, onOptionSelected: (Int) -> Unit) {
    val questionHashMap = hashMapOf(
        "A" to quizState.shuffledOption[0],
        "B" to quizState.shuffledOption[1],
        /*"C" to quizState.shuffledOption[2],
        "D" to quizState.shuffledOption[3]*/
    )
    if (quizState.shuffledOption.size > 2) {
        questionHashMap["C"] = quizState.shuffledOption[2]
        questionHashMap["D"] = quizState.shuffledOption[3]
    }

    Column(Modifier.verticalScroll(rememberScrollState())) {

        Row {
            Text(
                text = "$questionNo.",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = quizState.quiz.question,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        questionHashMap.onEachIndexed { index, (optionName, optionText) ->
            QuizOption(
                optionName = optionName,
                optionText = optionText,
                selected = quizState.selectedOption == index,
                onOptionClick = {
                    onOptionSelected(index)
                },
                onUnSelectOption = {
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
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp)
                    .shimmerEffect()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        questionHashMap.onEachIndexed { index, (_, _) ->
            QuizOptionShimmer(
            )
        }
    }

}