package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentGameMainMenuBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.Level

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

        }



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