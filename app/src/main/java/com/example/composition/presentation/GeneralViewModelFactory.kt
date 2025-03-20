package com.example.composition.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composition.domain.entity.Level
import com.example.composition.presentation.choose_level_fragment.ChooseLevelFragmentViewModel
import com.example.composition.presentation.custom_game_fragment.CustomGameViewModel
import com.example.composition.presentation.game_finish_fragment.FinishFragmentViewModel
import com.example.composition.presentation.game_finish_fragment.GameFinishFragment
import com.example.composition.presentation.game_fragment.GameViewModel
import com.example.composition.presentation.main_menu_fragment.MainMenuFragmentViewModel

class GeneralViewModelFactory(
    private val params:List<Any>?,
    private val application: Application,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            val level = params?.find { it is Level } as? Level
                ?: throw IllegalArgumentException("Level parameter not provided!")
            return GameViewModel(application, level) as T
        }

        if (modelClass.isAssignableFrom(MainMenuFragmentViewModel::class.java)){
            return MainMenuFragmentViewModel() as T
        }

        if (modelClass.isAssignableFrom(ChooseLevelFragmentViewModel::class.java)){
            return ChooseLevelFragmentViewModel() as T
        }

        if (modelClass.isAssignableFrom(FinishFragmentViewModel::class.java)){
            return FinishFragmentViewModel() as T
        }

        if (modelClass.isAssignableFrom(CustomGameViewModel::class.java)){
            return CustomGameViewModel() as T
        }

        throw RuntimeException("Unknown viewmodel class $modelClass ")
    }
}