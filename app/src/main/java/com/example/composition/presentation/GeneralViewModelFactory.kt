package com.example.composition.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composition.domain.entity.Level

class GeneralViewModelFactory(
   // private val level: Level,
    private val params:List<Any>?,
    private val application: Application,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            val level = params?.find { it is Level } as? Level
                ?: throw IllegalArgumentException("Level parameter not provided!")
            return GameViewModel(application, level) as T
        }




        throw RuntimeException("Unknown viewmodel class $modelClass ")
    }
}