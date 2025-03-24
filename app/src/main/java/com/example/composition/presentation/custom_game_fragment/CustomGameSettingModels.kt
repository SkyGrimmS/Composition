package com.example.composition.presentation.custom_game_fragment

sealed interface CustomGameSettingScreenEvent {

    data class OnCountOfRightAnswer(val count: Int) : CustomGameSettingScreenEvent

    data class OnMaxSumValue(val maxSum: Int) : CustomGameSettingScreenEvent

    data class OnPercentOfRightAnswer(val percent: Int) : CustomGameSettingScreenEvent

    data class OnGameTime(val gameTime: Int) : CustomGameSettingScreenEvent
}

data class CustomGameSettingScreenState(
    val maxSum: Int = -1,
    val maxCount: Int = -1
)