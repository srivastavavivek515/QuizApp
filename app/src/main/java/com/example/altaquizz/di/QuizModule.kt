package com.example.altaquizz.di

import android.util.*
import com.example.altaquizz.quizusecases.*
import com.example.altaquizz.retrofit.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import okhttp3.*
import okhttp3.logging.*
import retrofit2.*
import retrofit2.converter.gson.*
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object QuizModule {

    @Singleton
    @Provides
    fun getQuizApi(client: OkHttpClient):QuizApi{
        return Retrofit.Builder().baseUrl("https://opentdb.com/").client(client).addConverterFactory(GsonConverterFactory.create()).build().create(QuizApi::class.java)
    }

    @Singleton
    @Provides
    fun getQuizRepoImpl(quizApi: QuizApi):QuizRepo{
        return QuizRepoImpl(quizApi)
    }

    @Singleton
    @Provides
    fun getUseCasesRepo(quizRepo: QuizRepo):GetQuizUseCases{
        return GetQuizUseCases(quizRepo)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            Log.d("RetrofitLog", message)  // Logs full body, headers, and URL
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
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
}