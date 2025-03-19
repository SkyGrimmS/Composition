package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentWelcomeBinding
class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        with(binding) {
            btnWelcome.setOnClickListener {
                launchChooseLevelFragment()
            }
        }
    }

    private fun launchChooseLevelFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_chooseLevelFragment)
    }
}
