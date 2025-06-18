package com.example.altaquizz.navigation

import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.altaquizz.di.component.*
import com.example.altaquizz.screens.*
import com.example.altaquizz.viewmodel.*

@Composable
fun SetUpNavGraph() {
    val navHostController = rememberNavController()
    val component = QuizComponent()
    NavHost(navHostController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            val viewModel: HomeViewModel = component.homeViewModel
            val state by viewModel.homeState.collectAsState()
            HomeScreen(state, navController = navHostController, viewModel::event)
        }
        composable<QuizScreen> {
            val viewModel: QuizViewModel = component.quizViewModel
            val state by viewModel.quizList.collectAsState()
            val args = it.toRoute<QuizScreen>()
            QuizScreen(
                navHostController,
                noOfQuiz = args.noOfQuestion,
                category = args.category,
                quizDifficulty = args.difficulty,
                type = args.type,
                state = state,
                viewModel::onEvent
            )
        }
        composable<ScoreScreen> {
            val viewModel: QuizViewModel = component.quizViewModel
            val state by viewModel.quizList.collectAsState()
            ScoreScreen(navHostController, state.score)
        }
    }

}