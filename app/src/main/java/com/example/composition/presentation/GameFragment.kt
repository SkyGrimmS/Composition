package com.example.composition.presentation


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.composition.R
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.Level
import com.example.composition.utils.KEY_LEVEL

class GameFragment : Fragment() {
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        val tvOptions = getAnswerOptionsList()
        val level = getLevel()

        setListeners(tvOptions)
        getAnswerOptionsList()
        setupObservers(tvOptions)

        viewModel.startGame(level)
        return binding.root
    }

    private fun setListeners(tvOptions: MutableList<TextView>) {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun launchGameFinishFragment(result: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishFragment.newInstance(result))
            .addToBackStack(null)
            .commit()
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
                pbScore.secondaryProgress = it
            }

            viewModel.gameResult.observe(viewLifecycleOwner) {
                launchGameFinishFragment(it)
            }

            viewModel.progressAnswers.observe(viewLifecycleOwner){
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

    private fun getLevel():Level {
        return requireArguments().getParcelable(KEY_LEVEL, Level::class.java)
            ?: throw IllegalArgumentException("Level argument is missing!")
    }

    companion object {
        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }

        const val NAME = "game_fragment"
    }
}