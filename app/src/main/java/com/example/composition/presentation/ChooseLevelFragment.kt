package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private lateinit var binding: FragmentChooseLevelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChooseLevelBinding.inflate(layoutInflater)

        setListeners()

        return binding.root
    }

    private fun setListeners() {
        with(binding) {

            listOf(
                btnLevelTest to Level.TEST,
                btnLevelEasy to Level.EASY,
                btnLevelNormal to Level.NORMAL,
                btnLevelHard to Level.HARD
            ).forEach { (button, level) ->
                button.setOnClickListener { launchGameFragment(level) }
            }

            btnBack.setOnClickListener {
                launchMainMenuFragment()
            }
        }
    }

    private fun launchMainMenuFragment() {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameMainMenuFragment()
        )
    }


    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level)
        )
    }
}