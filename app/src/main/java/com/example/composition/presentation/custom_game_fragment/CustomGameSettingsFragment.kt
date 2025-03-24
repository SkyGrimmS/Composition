package com.example.composition.presentation.custom_game_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentCustomGameSettingsBinding
import com.example.composition.domain.entity.GameSettings
import com.example.composition.presentation.GeneralViewModelFactory
import com.example.composition.utils.isFieldValid
import com.example.composition.utils.isParamMoreThanOne
import com.example.composition.utils.isSumMoreThanSeven
import com.google.android.material.textfield.TextInputLayout


class CustomGameSettingsFragment : Fragment() {
    private lateinit var binding: FragmentCustomGameSettingsBinding

    private val viewModelFactory by lazy {
        GeneralViewModelFactory(listOf(), requireActivity().application)
    }
    private val viewModel: CustomGameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CustomGameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCustomGameSettingsBinding.inflate(layoutInflater)
        setListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    private fun setListeners() {
        with(binding) {

            btnBack.setOnClickListener {
                launchMainMenuFragment()
            }
            btnStartGame.setOnClickListener {

                launchGameFragment()
            }


        }
        setAfterTextChangedListeners()
    }


    private fun setAfterTextChangedListeners() {
        with(binding) {

            etSumValue.doAfterTextChanged {
                parseMaxSumValue(it.toString().trim(), tilMaxSumValue)
            }
            etMinCountOfRightAnswers.doAfterTextChanged {
                parseMinCountOfRightAnswers(it.toString().trim())
            }
            etMinPercentOfRightAnswers.doAfterTextChanged {
                parseMinPercentOfRightAnswers(it.toString().trim())
            }
            etGameTime.doAfterTextChanged {
                parseGameTime(it.toString().trim())
            }

        }
    }


    private fun parseMaxSumValue(maxSum: String, tilMaxSumValue: TextInputLayout) {
        if (maxSum.isFieldValid() && maxSum.isSumMoreThanSeven()) {
            viewModel.onEvent(CustomGameSettingScreenEvent.OnMaxSumValue(maxSum.toInt()))
            tilMaxSumValue.setError(null)
        } else {
            tilMaxSumValue.setError("text")
        }
    }


    private fun parseMinCountOfRightAnswers(count: String): Boolean {
        return count.isParamMoreThanOne()
    }

    private fun parseGameTime(gameTime: String): Boolean {
        return gameTime.isParamMoreThanOne()
    }

    private fun parseMinPercentOfRightAnswers(percent: String): Boolean {
        return percent.isParamMoreThanOne()
    }


    private fun launchMainMenuFragment() {
        findNavController().navigate(
            CustomGameSettingsFragmentDirections.actionCustomGameSettingsFragmentToGameMainMenuFragment()
        )
    }

    private fun launchGameFragment(gameSettings: GameSettings) {
        findNavController().navigate(
            CustomGameSettingsFragmentDirections.actionCustomGameSettingsFragmentToGameFragment(
                gameSettings
            )
        )
    }

}