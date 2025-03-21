
package com.example.composition.presentation.custom_game_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentCustomGameSettingsBinding
import com.example.composition.domain.entity.GameSettings
import com.example.composition.presentation.GeneralViewModelFactory
import com.example.composition.presentation.game_fragment.GameViewModel


class CustomGameSettingsFragment : Fragment() {
    private lateinit var binding: FragmentCustomGameSettingsBinding

    private val userGameSettings = GameSettings(7,5,7.0, 10)

    private val viewModelFactory by lazy {
        GeneralViewModelFactory(listOf(userGameSettings), requireActivity().application)
    }
    private val viewModel: GameViewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCustomGameSettingsBinding.inflate(layoutInflater)
        setListeners()

        return binding.root

    }

    private fun setListeners(){
        with(binding) {

            btnBack.setOnClickListener {
                launchMainMenuFragment()
            }

            btnStartGame.setOnClickListener {
                launchGameFragment(userGameSettings)
             }
        }
    }

    private fun launchMainMenuFragment(){
        findNavController().navigate(
            CustomGameSettingsFragmentDirections.actionCustomGameSettingsFragmentToGameMainMenuFragment()
        )
    }

    private fun launchGameFragment(gameSettings: GameSettings) {
        findNavController().navigate(
            CustomGameSettingsFragmentDirections.actionCustomGameSettingsFragmentToGameFragment(gameSettings)
        )
    }

}