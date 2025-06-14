package com.example.altaquizz.navigation

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.altaquizz.screens.*
import com.example.altaquizz.viewmodel.*

@Composable
fun setUpNavGraph() {
   val navHostController =  rememberNavController()

    NavHost(navController = navHostController, startDestination = Routes.HomeScreen.route){
        composable(route = Routes.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(state, navController = navHostController,viewModel::event)
        }
        composable(route= Routes.QuizScreen.route, arguments = listOf(
            navArgument(name = ARG_KEY_NO_OF_QUESTION){
                type = NavType.IntType
            },
            navArgument(name = ARG_KEY_CATEGORY){
                type = NavType.StringType
            },
            navArgument(name = ARG_KEY_DIFFICULTY){
                type = NavType.StringType
            },
            navArgument(name = ARG_KEY_TYPE){
                type = NavType.StringType
            },

        ) ) {


            val no = it.arguments?.getInt(ARG_KEY_NO_OF_QUESTION) ?: 0
            val category = it.arguments?.getString(ARG_KEY_CATEGORY)
            val type = it.arguments?.getString(ARG_KEY_TYPE)
            val difficulty = it.arguments?.getString(ARG_KEY_DIFFICULTY)
            val viewModel: QuizViewModel = hiltViewModel()
            val state by viewModel.quizList.collectAsState()
            QuizScreen(navHostController,noOfQuiz = no, category = category!!, quizDifficulty = difficulty!!,type = type!!, state = state,viewModel::onEvent)

        }

    }

}