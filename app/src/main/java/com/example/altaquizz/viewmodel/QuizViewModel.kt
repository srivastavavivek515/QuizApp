package com.example.altaquizz.viewmodel

import android.util.*
import androidx.lifecycle.*
import com.example.altaquizz.Resources.*
import com.example.altaquizz.data.*
import com.example.altaquizz.event_state.*
import com.example.altaquizz.quizusecases.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


class QuizViewModel (private val getQuizUseCases: GetQuizUseCases) : ViewModel() {

    private val _quizList= MutableStateFlow(StateQuizScreen())
    val quizList = _quizList
    fun onEvent(event: EventQuizScreen){
        when(event){
            is EventQuizScreen.GetQuiz ->{
                getQuiz(event.no,event.category,event.difficulty,event.type)
            }
            is EventQuizScreen.SetOptionSelected ->{
                updateQuizStateList(event.quizStateIndex,event.selectedOption)
            }
        }

    }

  private  fun updateQuizStateList(quizStateIndex:Int,selectedOption:Int){
      val updatedQuizStateList = mutableListOf<QuizState>()

      quizList.value.quizStateList.forEachIndexed{
          index, quizState ->
          updatedQuizStateList.add(
              if(quizStateIndex == index){
                  quizState.copy(selectedOption = selectedOption)
              }else{
                  quizState
              }
          )


      }
      _quizList.value = quizList.value.copy(quizStateList =  updatedQuizStateList)

      updateScore(quizList.value.quizStateList[quizStateIndex])

    }

    private fun updateScore(quizState: QuizState){
        if(quizState.selectedOption == -1){
            return
        }
        val correctAnswer = quizState.quiz.correct_answer
        val selectedAnswer = quizState.selectedOption.let {
            quizState.shuffledOption[it]
        }
        if(correctAnswer == selectedAnswer){
            val prevScore = _quizList.value.score
            _quizList.value = _quizList.value.copy(score = prevScore+1)
        }
    }
   private fun getQuiz(no:Int,category:Int,difficulty:String,type:String){
        viewModelScope.launch {
            getQuizUseCases.getAllQuizzes(no,category,difficulty,type).collect{
                    resources ->
                when(resources){
                    is Resource.Loading->{
                        Log.d(">>>>>>>>>>>>> loading","****")
                     quizList.value = StateQuizScreen(isLoading = true)
                    }
                    is Resource.Success->{
                       val quizStateList =  getQuizState(resources.data!!)
                    quizList.value = StateQuizScreen(quizStateList = quizStateList)
                    }
                    is Resource.Error ->{
                        quizList.value = StateQuizScreen(error = resources.message!!)

                    }
                }
            }
        }


    }

   private fun getQuizState(quizList:List<Quiz>):List<QuizState>{
        val quizStateList = mutableListOf<QuizState>()
        for(quiz in quizList){
                val shuffledOption = mutableListOf<String>().apply {
                    add(quiz.correct_answer)
                        addAll(quiz.incorrect_answers)
                        shuffle()
                }
            quizStateList.add(QuizState(quiz,shuffledOption,-1))
        }
        return quizStateList
    }
}