package com.example.altaquizz.di.component

import com.example.altaquizz.viewmodel.*
import org.koin.core.component.*

/**
 * @author Altametrics Inc. Created On 05/06/25 6:24 pm
 */
class QuizComponent : KoinComponent {
    val homeViewModel: HomeViewModel by inject()
    val quizViewModel: QuizViewModel by inject()
}