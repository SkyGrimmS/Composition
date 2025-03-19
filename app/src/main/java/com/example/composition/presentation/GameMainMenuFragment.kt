package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FragmentGameMainMenuBinding

class GameMainMenuFragment : Fragment() {
    private lateinit var binding: FragmentGameMainMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameMainMenuBinding.inflate(layoutInflater)
        return binding.root
    }




}