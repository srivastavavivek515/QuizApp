package com.example.altaquizz.screens

import android.util.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import com.example.altaquizz.R
import com.example.altaquizz.components.*
import com.example.altaquizz.constants.*
import com.example.altaquizz.event_state.*
import com.example.altaquizz.navigation.*


@Composable
fun HomeScreen(
    state: StateHomeScreen,
    navController: NavHostController,
    event: (EventHomeScreen) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Header()
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AdvancedDropdownMenu(
                "No Of Question",
                Constants.noOfQuestion,
                state.noOfQuestion.toString(),
                onDropDownChange = {
                    event(EventHomeScreen.SetNoOfQuestion(data = it))
                })
            AdvancedDropdownMenu(
                "Select Category",
                Constants.category,
                state.category,
                onDropDownChange = {
                    event(EventHomeScreen.SetCategory(data = it))
                })
            AdvancedDropdownMenu(
                "Difficulty",
                Constants.difficulty,
                state.difficulty,
                onDropDownChange = {
                    event(EventHomeScreen.SetDifficulty(data = it))
                })
            AdvancedDropdownMenu("Answer Type", Constants.type, state.type, onDropDownChange = {
                event(EventHomeScreen.SetType(data = it))
            })

            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .background(Color.Red)
            )
            RoundedCornerButton(
                modifier = Modifier.fillMaxWidth(1f),
                "Start Exam",
                colorResource(id = R.color.black),
                Color.White
            ) {
                Log.d(
                    ">>>>>>>>>>",
                    "*********  ${state.noOfQuestion}  ${state.category}  ${state.difficulty}  ${state.type}"
                )
                navController.navigate(
                    QuizScreen(
                        state.noOfQuestion,
                        state.category,
                        state.difficulty,
                        state.type
                    )
                )
            }
        }
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = colorResource(id = R.color.headerColor), shape = RoundedCornerShape(
                    bottomStart = 30.dp, bottomEnd = 30.dp
                )
            ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                painter = painterResource(id = R.drawable.menu_open),
                contentDescription = "menu_button",
                tint = colorResource(
                    id = R.color.iconColor
                )
            )
            Text(
                text = "Quiz",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = colorResource(
                    id = R.color.white
                )
            )
            Icon(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                painter = painterResource(id = R.drawable.contact_mail),
                contentDescription = "contact_button",
                tint = colorResource(
                    id = R.color.iconColor
                )
            )
        }
    }
}