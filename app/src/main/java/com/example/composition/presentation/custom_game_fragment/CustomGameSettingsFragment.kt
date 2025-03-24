
package com.example.composition.presentation.custom_game_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.composition.databinding.FragmentCustomGameSettingsBinding
import com.example.composition.domain.entity.GameSettings
import com.example.composition.presentation.GeneralViewModelFactory
import com.example.composition.utils.isFieldValid
import com.example.composition.utils.isParamMoreThanOne
import com.example.composition.utils.isSumMoreThanSeven


class CustomGameSettingsFragment : Fragment() {
    private lateinit var binding: FragmentCustomGameSettingsBinding

    private val viewModelFactory by lazy {
        GeneralViewModelFactory(listOf(), requireActivity().application)
    }
    private val viewModel: CustomGameViewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[CustomGameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCustomGameSettingsBinding.inflate(layoutInflater)
        setListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



    private fun setListeners(){
        with(binding) {

            btnBack.setOnClickListener {
                launchMainMenuFragment()
            }
            btnStartGame.setOnClickListener {

                launchGameFragment()
             }


        }
        setAfterTextChangedListeners()
    }



    private fun setAfterTextChangedListeners(){
        with(binding){

            etSumValue.doAfterTextChanged {
                parseMaxSumValue(it.toString().trim().toIntOrNull())
            }
            etMinCountOfRightAnswers.doAfterTextChanged {
                parseMinCountOfRightAnswers(it.toString().trim().toIntOrNull()))
            }
            etMinPercentOfRightAnswers.doAfterTextChanged {
                parseMinPercentOfRightAnswers(it.toString().trim().toIntOrNull()))
            }
            etGameTime.doAfterTextChanged {
                parseGameTime(it.toString().trim().toIntOrNull()))
            }

        }
    }





    private fun parseMaxSumValue(maxSum:Int?){
        maxSum?.let {
            if (maxSum.isSumMoreThanSeven()){
                viewModel.onEvent(EventCustomGS.OnMaxSumValue(maxSum))
                viewModel.resetErrorInputMaxSum()
            }else{

            }
        }
    }

    private fun parseMinCountOfRightAnswers(count:Int?){


    }

    private fun parseMinPercentOfRightAnswers(percent:Int?){

    }

    private fun parseGameTime(gameTime:Int?){

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