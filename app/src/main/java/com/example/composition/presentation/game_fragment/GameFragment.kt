package com.example.composition.presentation.game_fragment


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import com.example.composition.presentation.GeneralViewModelFactory

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GeneralViewModelFactory(listOf(), requireActivity().application)
    }

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        val tvOptions = getAnswerOptionsList()

        getAnswerOptionsList()
        setupObservers(tvOptions)

        viewModel.setupGame(args.gameSettings)
        return binding.root
    }

    private fun setupObservers(tvOptions: MutableList<TextView>) {
        with(binding) {

            viewModel.question.observe(viewLifecycleOwner) { question ->
                tvSum.text = String.format(question.sum.toString())
                tvLeftNumber.text = String.format(question.visibleNumber.toString())

                for (i in 0 until tvOptions.size) {
                    with(tvOptions[i]) {
                        this.text = String.format(question.options[i].toString())
                        this.setOnClickListener {
                            viewModel.chooseAnswer(question.options[i])
                        }
                    }

                }
            }

            viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
                pbScore.setProgress(it, true)
            }

            viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
                tvAnswersProgress.setTextColor(getColorByState(it))
            }

            viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
                val color = getColorByState(it)
                pbScore.progressTintList = ColorStateList.valueOf(color)
            }

            viewModel.formatedTime.observe(viewLifecycleOwner) {
                tvTimer.text = it
            }

            viewModel.minPercent.observe(viewLifecycleOwner) {
                pbScore.secondaryProgress = it.toInt()
            }

            viewModel.gameResult.observe(viewLifecycleOwner) {
                launchGameFinishFragment(it)
            }

            viewModel.progressAnswers.observe(viewLifecycleOwner) {
                tvAnswersProgress.text = it
            }
        }
    }



    private fun getAnswerOptionsList(): MutableList<TextView> {
        with(binding) {
            return mutableListOf(tvOption1, tvOption2, tvOption3, tvOption4, tvOption5, tvOption6)
        }
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }

        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameFinishFragment(result: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishFragment(result)
        )

    }
}