
package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentCustomGameSettingsBinding
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level


class CustomGameSettingsFragment : Fragment() {
    private lateinit var binding: FragmentCustomGameSettingsBinding

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

             }




        }
    }





    private fun launchMainMenuFragment(){
        findNavController().navigate(
            CustomGameSettingsFragmentDirections.actionCustomGameSettingsFragmentToGameMainMenuFragment()
        )
    }

    private fun launchGameFragment(gameSettings: GameSettings, ) {
        findNavController().navigate(
            CustomGameSettingsFragmentDirections.actionCustomGameSettingsFragmentToGameFragment()
        )
    }

}