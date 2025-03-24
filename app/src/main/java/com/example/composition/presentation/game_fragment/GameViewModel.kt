package com.example.composition.presentation.game_fragment

import android.annotation.SuppressLint
import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composition.R
import com.example.composition.data.GameRepositoryImpl
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Question
import com.example.composition.domain.usecases.GenerateQuestionUseCase
import kotlin.math.max

class GameViewModel(
    private val application: Application,
) : ViewModel() {

    private val repository = GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)

    private var timer: CountDownTimer? = null
    private var countOfRightAnswers = 0
    private var countOfQuestions = 0

    private val _formatedTime = MutableLiveData<String>()
    val formatedTime: LiveData<String>
        get() = _formatedTime

    private var _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int>
        get() = _percentOfRightAnswers

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    private val _enoughCountOfRightAnswers = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswers: LiveData<Boolean>
        get() = _enoughCountOfRightAnswers

    private val _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswers: LiveData<Boolean>
        get() = _enoughPercentOfRightAnswers

    private val _minPercent = MutableLiveData<Double>()
    val minPercent: LiveData<Double>
        get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private var maxSumValue = -1
    private var minPercentOfRightAnswers = -1.0
    private var minCountOfRightAnswers = -1

   fun setupGame(gameSettings: GameSettings) {
       maxSumValue = gameSettings.maxSumValue
       minPercentOfRightAnswers = gameSettings.minPercentOfRightAnswers.toDouble()
       minCountOfRightAnswers = gameSettings.minCountOfRightAnswers


       setMinPercent()
       startTimer(gameSettings)
       generateQuestion()
       updateProgress()
    }

    private fun onFinishGame(gameSettings: GameSettings) {
        _gameResult.value = GameResult(
            winner = enoughPercentOfRightAnswers.value == true && enoughCountOfRightAnswers.value == true,
            countOfRightAnswers = countOfRightAnswers,
            countOfQuestions = countOfQuestions,
            gameSettings = gameSettings
        )
    }

    private fun startTimer(gameSettings:GameSettings) {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {

            override fun onTick(millisUntilFinished: Long) {
                _formatedTime.value = onFormatTime(millisUntilFinished)
            }

            override fun onFinish() {
                onFinishGame(gameSettings)
            }
        }
        timer?.start()
    }

    @SuppressLint("DefaultLocale")
    private fun onFormatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d: %02d", minutes, leftSeconds)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    private fun setMinPercent() {
        _minPercent.value = minPercentOfRightAnswers
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(maxSumValue)
    }

    fun chooseAnswer(number: Int,) {
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }

    private fun checkAnswer(number: Int) {
        val rightAnswer = question.value?.rightAnswer

        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++
    }

    private fun calculatePercentOfRightAnswers(): Int {
        if (countOfRightAnswers == 0) {
            return 0
        }
        return ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }

    @SuppressLint("StringFormatMatches")
    private fun updateProgress() {
        val percent = calculatePercentOfRightAnswers()
        _percentOfRightAnswers.value = percent

        _progressAnswers.value = String.format(
            application.resources.getString(R.string.description_right_answers),
            countOfRightAnswers,
            minCountOfRightAnswers
        )

        _enoughCountOfRightAnswers.value =
            countOfRightAnswers >= minCountOfRightAnswers
        _enoughPercentOfRightAnswers.value = percent >= minPercentOfRightAnswers
    }

    companion object {
        const val MILLIS_IN_SECONDS = 1000L
        const val SECONDS_IN_MINUTES = 60
    }
}