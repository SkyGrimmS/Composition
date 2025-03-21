package com.example.composition.presentation.choose_level_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.presentation.GeneralViewModelFactory

class ChooseLevelFragment : Fragment() {



    private lateinit var binding: FragmentChooseLevelBinding

    private val viewModelFactory by lazy {
        GeneralViewModelFactory(listOf(), requireActivity().application)
    }
    private val viewModel: ChooseLevelViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ChooseLevelViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChooseLevelBinding.inflate(
            layoutInflater
        )

        setListeners()

        return binding.root
    }

    private fun setListeners() {
        with(binding) {
            getLevel()

            btnBack.setOnClickListener {
                launchMainMenuFragment()
            }

        }
    }


    private fun getLevel() {
        with(binding) {
            listOf(
                btnLevelTest to Level.TEST,
                btnLevelEasy to Level.EASY,
                btnLevelNormal to Level.NORMAL,
                btnLevelHard to Level.HARD
            ).forEach { (button, level) ->
                button.setOnClickListener {
                    val settings = viewModel.getGameSettingsByLevel(level)
                    launchGameFragment(settings)
                }
            }
        }
    }


    private fun launchMainMenuFragment() {
        findNavController().popBackStack()
    }


    private fun launchGameFragment(gameSettings: GameSettings) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(gameSettings)
        )
    }
}