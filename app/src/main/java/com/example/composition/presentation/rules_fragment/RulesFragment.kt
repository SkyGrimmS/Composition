package com.example.composition.presentation.rules_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRulesBinding.inflate(layoutInflater)

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        with(binding) {
            btnGotItRules.setOnClickListener {
                launchMainMenuFragment()
            }
        }
    }

    private fun launchMainMenuFragment(){
        findNavController().navigate(
            RulesFragmentDirections.actionRulesFragmentToGameMainMenuFragment()
        )
    }

}
