package com.example.composition.presentation.custom_game_fragment

sealed interface EventCustomGS {

    data class OnCountOfRightAnswer(val count: Int):EventCustomGS

    data class OnMaxSumValue(val maxSum: Int):EventCustomGS

    data class OnPercentOfRightAnswer(val percent: Int):EventCustomGS

    data class OnGameTime(val gameTime: Int):EventCustomGS
}