package com.example.altaquizz.di.component

import com.example.altaquizz.viewmodel.*
import org.koin.core.component.*

/**
 * @author Altametrics Inc. Created On 05/06/25 6:24 pm
 */
class QuizComponent : KoinComponent {
    /*val quizApi: QuizApi by inject()
    val quizRepoImpl: QuizRepo by inject()
    val useClassRepo : GetQuizUseCases by inject()
    val loggingInterceptor : HttpLoggingInterceptor by inject()
    val okHttpClient : OkHttpClient by inject()*/
    val homeViewModel: HomeViewModel by inject()
    val quizViewModel: QuizViewModel by inject()
}