package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.databinding.FragmentGameFinishBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.utils.KEY_RESULT
import com.example.composition.utils.handleOnBackPressed

class GameFinishFragment : Fragment() {
    private lateinit var binding: FragmentGameFinishBinding
    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameFinishBinding.inflate(layoutInflater)

        parseArgs()
        setListeners()

        return binding.root
    }

    private fun setListeners(){
        with(binding){

            btnGameFinish.setOnClickListener {
                onRetryGame()
            }
        }

        requireActivity().handleOnBackPressed(lifecycle = this){
            onRetryGame()
        }
    }

    private fun onRetryGame(){
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    private fun parseArgs() {
        requireArguments().let { args ->
             gameResult = args.getParcelable(KEY_RESULT, GameResult::class.java)
                ?: throw IllegalArgumentException("Game result argument is missing!")
        }
    }

    companion object {
        fun newInstance(result: GameResult): GameFinishFragment {
            return GameFinishFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_RESULT, result)
                }
            }
        }
    }
}