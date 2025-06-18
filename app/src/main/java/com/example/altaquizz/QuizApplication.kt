package com.example.altaquizz

import android.app.*
import com.example.altaquizz.di.modules.*
import org.koin.core.context.*

class QuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(quizModule)
        }
    }
}