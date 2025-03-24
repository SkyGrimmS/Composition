package com.example.composition.presentation.custom_game_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentCustomGameSettingsBinding
import com.example.composition.domain.entity.GameSettings
import com.example.composition.presentation.GeneralViewModelFactory
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }


    private fun setListeners() {
        with(binding) {

            btnBack.setOnClickListener {
                launchMainMenuFragment()
            }
            btnStartGame.setOnClickListener {
                viewModel.getGameSettings()?.let { settings -> launchGameFragment(settings) }
            }

        }
        setAfterTextChangedListeners()
    }

    private fun setObservers() {
        viewModel.isFieldsFilled.observe(viewLifecycleOwner) { isFilled ->
            binding.btnStartGame.isClickable = isFilled == true
        }
    }


    private fun setAfterTextChangedListeners() {
        with(binding) {

            etSumValue.doAfterTextChanged {
                parseMaxSumValue(it.toString().trim().toIntOrNull(), tilMaxSumValue)
            }
            etMinCountOfRightAnswers.doAfterTextChanged {
                parseMinCountOfRightAnswers(
                    it.toString().trim().toIntOrNull(),
                    tilMinCountOfRightAnswers
                )
            }
            etMinPercentOfRightAnswers.doAfterTextChanged {
                parseMinPercentOfRightAnswers(
                    it.toString().trim().toIntOrNull(),
                    tilMinPercentOfRightAnswers
                )
            }
            etGameTime.doAfterTextChanged {
                parseGameTime(it.toString().trim().toIntOrNull(), tilGameTime)
            }

        }
    }


    private fun parseMaxSumValue(maxSum: Int?, til: TextInputLayout) {
        maxSum?.let {
            if (maxSum.isSumMoreThanSeven()) {
                viewModel.onEvent(CustomGameSettingModels.OnMaxSumValue(maxSum))
                til.setError(null)
            } else {
                til.setError(getText(R.string.error_max_sum_value))
            }
        }
    }

    private fun parseMinCountOfRightAnswers(count: Int?, til: TextInputLayout) {
        count?.let {
            if (count.isParamMoreThanOne()) {
                viewModel.onEvent(CustomGameSettingModels.OnCountOfRightAnswer(count))
                til.setError(null)
            } else {
                til.setError(getText(R.string.error_min_count_of_right_answers))
            }
        }
    }

    private fun parseMinPercentOfRightAnswers(percent: Int?, til: TextInputLayout) {
        percent?.let {
            if (percent.isParamMoreThanOne()) {
                viewModel.onEvent(CustomGameSettingModels.OnPercentOfRightAnswer(percent))
                til.setError(null)
            } else {
                 til.setError(getText(R.string.error_min_percent_of_right_answers))
            }
        }
    }

    private fun parseGameTime(gameTime: Int?, til: TextInputLayout) {
        gameTime?.let {
            if (gameTime.isParamMoreThanOne()) {
                viewModel.onEvent(CustomGameSettingModels.OnGameTime(gameTime))
                til.setError(null)
            } else {
                til.setError(getText(R.string.error_min_percent_of_right_answers))
            }
        }
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