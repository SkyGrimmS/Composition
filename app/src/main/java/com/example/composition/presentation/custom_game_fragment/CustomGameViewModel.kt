package com.example.composition.presentation.custom_game_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composition.domain.entity.GameSettings

class CustomGameViewModel : ViewModel() {

    private var dataGameSettings: GameSettings? = null

    private val _errorInputMaxSum = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> get() = _errorInputMaxSum

    private val _errorInputMinCountOfRightAnswers = MutableLiveData<Boolean>()
    val errorInputMinCountOfRightAnswers: LiveData<Boolean> get() = _errorInputMinCountOfRightAnswers

    private val _errorInputMinPercentOfRightAnswers = MutableLiveData<Boolean>()
    val errorInputMinPercentOfRightAnswers: LiveData<Boolean> get() = _errorInputMinPercentOfRightAnswers

    private val _errorInputTime = MutableLiveData<Boolean>()
    val errorInputTime: LiveData<Boolean> get() = _errorInputTime

    fun onEvent(event: EventCustomGS) {
        when (event) {
            is EventCustomGS.OnMaxSumValue -> {

            }
            is EventCustomGS.OnCountOfRightAnswer -> {

            }
            is EventCustomGS.OnGameTime -> {

            }
            is EventCustomGS.OnPercentOfRightAnswer -> {

            }
        }



    }


    fun resetErrorInputMaxSum() {
        _errorInputMaxSum.value = false
    }

    fun resetErrorInputMinCountOfRightAnswers() {
        _errorInputMinCountOfRightAnswers.value = false
    }

    fun resetInputMinPercentOfRightAnswers() {
        _errorInputMinPercentOfRightAnswers.value = false
    }

    fun resetErrorInputTime() {
        _errorInputMinCountOfRightAnswers.value = false
    }


}