package com.example.altaquizz.quiz

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import com.example.altaquizz.R
import com.example.altaquizz.constants.*
import com.example.altaquizz.home.components.*
import com.example.altaquizz.main_activity.*
import com.example.altaquizz.quiz.event_state.*
import kotlinx.coroutines.*

@Composable
fun QuizScreen(navHostController: NavHostController,noOfQuiz:Int,category:String,quizDifficulty:String,type:String,state:StateQuizScreen,eventQuizScreen: (EventQuizScreen)->Unit) {
    val type = when(type){
        "Multiple Choice"-> "multiple"
        else ->"boolean"
    }
    LaunchedEffect(key1 = Unit){
        eventQuizScreen(EventQuizScreen.GetQuiz(noOfQuiz,Constants.categoryMap.get(category)!!,quizDifficulty.lowercase(),type))
    }
    Column(modifier = Modifier.fillMaxSize()) {

       QuizAppBar(category = category) {
           navHostController.popBackStack()
       }

        Column(Modifier.padding(10.dp)) {
            Box (modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Questions: $noOfQuiz",style = TextStyle(color = Color.White))
                Text(text = quizDifficulty, style = TextStyle(color = Color.White))

            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(color = Color.White)) {

            }
            Spacer(modifier = Modifier.height(30.dp))
            val pagerState = rememberPagerState {
                state.quizStateList.size
            }
             if(isQuizFetched(state = state)){

                 HorizontalPager(state = pagerState) {index->
                     QuestionScreen(index+1,state.quizStateList[index], onOptionSelected = {
                         selectedIndex-> eventQuizScreen(EventQuizScreen.SetOptionSelected(index,selectedIndex))
                     })
                 }

             }
            Spacer(modifier = Modifier.height(50.dp))

            val buttonText by remember {
                derivedStateOf {
                    when(pagerState.currentPage){
                        0 ->{
                            listOf("","Next")
                        }
                        state.quizStateList.size-1->{
                            listOf("Previous","Submit")
                        }
                        else ->{
                            listOf("Previous","Next")
                        }
                    }
                }
            }
            val scope = rememberCoroutineScope()
            Row(horizontalArrangement = Arrangement.SpaceBetween ) {

                if(buttonText[0].isNotEmpty()){
                    RoundedCornerButton(text = buttonText[0] , textColor = colorResource(id = R.color.black), containerColor = Color.White) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage-1)
                        }
                    }
                }

                Spacer(modifier = Modifier.width(30.dp))
                RoundedCornerButton(text = buttonText[1], textColor = Color.White,containerColor = colorResource(id = R.color.iconColor)) {
                    scope.launch {
                        if(pagerState.currentPage == state.quizStateList.size-1){
                        //TODO::
                        }else{
                            pagerState.animateScrollToPage(pagerState.currentPage+1)

                        }
                    }
                }
            }
        }



    }
}

@Composable
fun isQuizFetched(state: StateQuizScreen):Boolean {
    return when {
        state.isLoading -> {
            ShimmerQuestionScreen()
            false
        }
        state.quizStateList.isNotEmpty() -> {
            true
        }
        else -> {
            Text(text = state.error.toString())
            false
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun QuizAppBar(category: String, onBackClick: () -> Unit) {
        TopAppBar(modifier = Modifier.fillMaxWidth(),
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
