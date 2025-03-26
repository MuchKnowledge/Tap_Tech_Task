package com.example.modern.feature.main.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.common_util.safeClick
import com.example.common_util.shortToast
import com.example.entity.PointEntity
import com.example.modern.common.viewBinding
import com.example.modern.feature.result.presentation.ResultFragment
import com.example.tapYou.R
import com.example.tapYou.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding<FragmentMainBinding>()
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeState()
    }

    private fun setListeners() {
        binding.goButton.safeClick {
            val countText = binding.countEdit.text.toString().toIntOrNull() ?: 0
            viewModel.onEvent(MainEvent.ButtonClick(countText))
        }
    }

    private fun observeState() {
        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )
    }

    private fun handleState(state: MainState) {
        if (state.isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun handleSideEffect(sideEffect: MainSideEffect) {
        when (val result = sideEffect) {
            is MainSideEffect.ShowCountError -> requireActivity().shortToast(getString(R.string.main_screen_count_error))
            is MainSideEffect.ShowNetworkError -> requireActivity().shortToast(result.message)
            is MainSideEffect.NavigateToResult -> navigateToResult(result.points)
        }
    }

    private fun navigateToResult(points: List<PointEntity>) {
        val bundle = bundleOf(ResultFragment.POINTS_KEY to ArrayList(points))
        findNavController().navigate(R.id.action_to_resul_fragment, bundle)
    }

    private fun showLoading() {
        binding.goButton.isEnabled = false
        binding.countEdit.isEnabled = false

        binding.loadingOverlay.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).setDuration(ANIMATION_DURATION).start()
        }

        binding.loadingContainer.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).setDuration(ANIMATION_DURATION).start()
        }
    }

    private fun hideLoading() {
        binding.goButton.isEnabled = true
        binding.countEdit.isEnabled = true

        binding.loadingOverlay.animate()
            .alpha(0f)
            .setDuration(ANIMATION_DURATION)
            .withEndAction { binding.loadingOverlay.visibility = View.GONE }
            .start()

        binding.loadingContainer.animate()
            .alpha(0f)
            .setDuration(ANIMATION_DURATION)
            .withEndAction { binding.loadingContainer.visibility = View.GONE }
            .start()
    }

    companion object {
        const val ANIMATION_DURATION = 200L
    }
}