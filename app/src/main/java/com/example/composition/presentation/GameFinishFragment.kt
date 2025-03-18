package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishBinding

class GameFinishFragment : Fragment() {

    private val args by navArgs<GameFinishFragmentArgs>()

    private lateinit var binding: FragmentGameFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameFinishBinding.inflate(layoutInflater)

        setListeners()
        bindViews()

        return binding.root
    }

    private fun setListeners() {
        with(binding) {

            btnGameFinish.setOnClickListener {
                onRetryGame()
            }
        }
    }

    private fun bindViews() {
        with(binding) {

            ivEmojiResult.setImageResource(getSmileId())
            tvRequiredAnswers.text = String.format(
                getString(R.string.description_required_score_tv),
                args.result.gameSettings.minCountOfRightAnswers.toString()
            )

            tvScoreAnswers.text = String.format(
                getString(R.string.description_score_answers_tv),
                args.result.countOfRightAnswers.toString()
            )

            tvRequiredPercentage.text = String.format(
                getString(R.string.description_needed_answers_percentage_tv),
                args.result.gameSettings.minPercentOfRightAnswers.toString()
            )

            tvScorePercentage.text = String.format(
                getString(R.string.description_percentage_answers),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getSmileId(): Int {
        return if (args.result.winner) {
            R.drawable.happy_smile_icon
        } else {
            R.drawable.sad_smile_icon
        }
    }

    private fun getPercentOfRightAnswers() = with(args.result) {
        if (countOfRightAnswers == 0) {
            0
        } else {
            ((countOfRightAnswers) / countOfQuestions.toDouble() * 100).toString()
        }
    }

    private fun onRetryGame() {
        findNavController().popBackStack()
    }
}