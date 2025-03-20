package com.example.composition.presentation.main_menu_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentGameMainMenuBinding

class GameMainMenuFragment : Fragment() {
    private lateinit var binding: FragmentGameMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameMainMenuBinding.inflate(layoutInflater)

        setListeners()

        return binding.root
    }

    private fun setListeners(){
        with(binding){

            btnStartGame.setOnClickListener {
                launchChooseLevelFragment()
            }
            btnRules.setOnClickListener {
                launchRulesFragment()
            }

            bntCustomGame.setOnClickListener {
                launchCustomGameFragment()
            }

        }
    }

    private fun launchCustomGameFragment(){
        findNavController().navigate(
            GameMainMenuFragmentDirections.actionGameMainMenuFragmentToCustomGameSettingsFragment()
        )
    }

    private fun launchRulesFragment() {
        findNavController().navigate(
           GameMainMenuFragmentDirections.actionGameMainMenuFragmentToRulesFragment()
        )
    }

    private fun launchChooseLevelFragment() {
        findNavController().navigate(
            GameMainMenuFragmentDirections.actionGameMainMenuFragmentToChooseLevelFragment()
        )
    }
}