package com.example.composition.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.utils.KEY_LEVEL

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var level: Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)

        parseArgs()
        setListeners()

        return binding.root
    }

    private fun setListeners() {
        with(binding) {
            tvOption1.setOnClickListener {
                // Just plug
                launchGameFinishFragment(GameResult(true, 70, 100, GameSettings(10, 10, 1, 20)))
            }
        }
    }

    private fun launchGameFinishFragment(result: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishFragment.newInstance(result))
            .addToBackStack(null)
            .commit()
    }

    private fun parseArgs() {
        level = requireArguments().getParcelable(KEY_LEVEL, Level::class.java)
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