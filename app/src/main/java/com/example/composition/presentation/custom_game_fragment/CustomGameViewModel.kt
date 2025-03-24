package com.example.composition.presentation.custom_game_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composition.domain.entity.GameSettings

class CustomGameViewModel : ViewModel() {

    private val _isFieldsFilled = MutableLiveData<Boolean>()
    val isFieldsFilled: LiveData<Boolean> get() = _isFieldsFilled


    private var screenState =
        MutableLiveData<CustomGameSettingScreenState>().apply {
            value = CustomGameSettingScreenState()
        }

    fun onEvent(event: CustomGameSettingModels) {
        when (event) {
            is CustomGameSettingModels.OnMaxSumValue -> {
                screenState.value = screenState.value?.copy(maxSum = event.maxSum)
            }

            is CustomGameSettingModels.OnCountOfRightAnswer -> {
                screenState.value = screenState.value?.copy(minCountOfRightAnswer = event.count)
            }

            is CustomGameSettingModels.OnGameTime -> {
                screenState.value = screenState.value?.copy(gameTime = event.gameTime)
            }

            is CustomGameSettingModels.OnPercentOfRightAnswer -> {
                screenState.value = screenState.value?.copy(minPercentOfRightAnswer = event.percent)
            }
        }
        validateGameSettings()
    }

    private fun validateGameSettings() {
        _isFieldsFilled.value = screenState.value?.let { state ->
            state.gameTime != DEFAULT_VALUE &&
                    state.maxSum != DEFAULT_VALUE &&
                    state.minCountOfRightAnswer != DEFAULT_VALUE &&
                    state.minPercentOfRightAnswer != DEFAULT_VALUE
        } ?: false

    }

    fun getGameSettings(): GameSettings? {
        return screenState.value?.let { state ->
            GameSettings(
                state.maxSum,
                state.minCountOfRightAnswer,
                state.minPercentOfRightAnswer,
                state.gameTime
            )
        }
    }

    companion object {
        const val DEFAULT_VALUE = -1
    }

}

