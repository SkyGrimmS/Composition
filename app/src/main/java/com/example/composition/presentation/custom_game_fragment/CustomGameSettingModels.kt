package com.example.composition.presentation.custom_game_fragment

sealed interface CustomGameSettingModels {

    data class OnCountOfRightAnswer(val count: Int):CustomGameSettingModels

    data class OnMaxSumValue(val maxSum: Int):CustomGameSettingModels

    data class OnPercentOfRightAnswer(val percent: Int):CustomGameSettingModels

    data class OnGameTime(val gameTime: Int):CustomGameSettingModels

}

data class CustomGameSettingScreenState(
    val maxSum: Int = -1,
    val minCountOfRightAnswer: Int = -1,
    val minPercentOfRightAnswer: Int = -1,
    val gameTime: Int = -1
)