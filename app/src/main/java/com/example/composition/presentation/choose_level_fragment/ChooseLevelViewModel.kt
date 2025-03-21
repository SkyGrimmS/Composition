package com.example.composition.presentation.choose_level_fragment


import androidx.lifecycle.ViewModel
import com.example.composition.data.GameRepositoryImpl
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.usecases.GetGameSettingsUseCase

class ChooseLevelViewModel() : ViewModel() {

    private val repository = GameRepositoryImpl
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    fun getGameSettingsByLevel(level: Level): GameSettings {
        return getGameSettingsUseCase(level)
    }

}