package com.example.altaquizz.di.modules

import android.util.*
import com.example.altaquizz.quizusecases.*
import com.example.altaquizz.retrofit.*
import com.example.altaquizz.viewmodel.*
import okhttp3.*
import okhttp3.logging.*
import org.koin.androidx.viewmodel.dsl.*
import org.koin.dsl.*
import retrofit2.*
import retrofit2.converter.gson.*

/**
 * @author Altametrics Inc. Created On 05/06/25 6:40 pm
 */

val quizModule = module {
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { getQuizApi(get()) }
    single { getQuizRepoImpl(get()) }
    single { getUseCasesRepo(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel{HomeViewModel()}
}


fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor { message ->
        Log.d("RetrofitLog", message)  // Logs full body, headers, and URL
    }
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}


fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            Log.d("FullURL", request.url.toString()) // Logs full URL
            chain.proceed(request)
        }
        .addInterceptor(loggingInterceptor)
        .build()
}

fun getQuizApi(client: OkHttpClient): QuizApi {
    return Retrofit.Builder().baseUrl("https://opentdb.com/").client(client).addConverterFactory(
        GsonConverterFactory.create()).build().create(QuizApi::class.java)
}

fun getQuizRepoImpl(quizApi: QuizApi): QuizRepo {
    return QuizRepoImpl(quizApi)
}

fun getUseCasesRepo(quizRepo: QuizRepo): GetQuizUseCases {
    return GetQuizUseCases(quizRepo)
}
