package com.example.composition.presentation.game_finish_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishBinding
import com.example.composition.domain.entity.GameResult

class GameFinishFragment : Fragment() {

    private val args by navArgs<GameFinishFragmentArgs>()

    private lateinit var binding: FragmentGameFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameFinishBinding.inflate(layoutInflater)
        val result = args.result
        setListeners()
        bindViews(result)

        return binding.root
    }

    private fun setListeners() {
        with(binding) {

            btnGameFinish.setOnClickListener {
                onRetryGame()
            }
        }
    }

    private fun bindViews(result: GameResult) {
        with(binding) {

            ivEmojiResult.setImageResource(getSmileId(result))
            tvRequiredAnswers.text = String.format(
                getString(R.string.description_required_score_tv),
                result.gameSettings.minCountOfRightAnswers
            )

            tvScoreAnswers.text = String.format(
                getString(R.string.description_score_answers_tv),
                result.countOfRightAnswers
            )

            tvRequiredPercentage.text = String.format(
                getString(R.string.description_needed_answers_percentage_tv),
                result.gameSettings.minPercentOfRightAnswers
            )

            tvScorePercentage.text = String.format(
                getString(R.string.description_percentage_answers),
                getPercentOfRightAnswers(result)
            )
        }
    }

    private fun getSmileId(result: GameResult): Int {
        return if (result.winner) {
            R.drawable.happy_smile_icon
        } else {
            R.drawable.sad_smile_icon
        }
    }

    private fun getPercentOfRightAnswers(result: GameResult): Float = with(result) {
        if (countOfRightAnswers != 0) {
            ((countOfRightAnswers) / countOfQuestions.toFloat() * 100)
        } else {
            this.countOfRightAnswers.toFloat()
        }
    }

    private fun onRetryGame() {
        findNavController().popBackStack()
    }
}