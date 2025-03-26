package com.example.modern.feature.result.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.entity.PointEntity
import com.example.modern.common.viewBinding
import com.example.tapYou.R
import com.example.tapYou.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {

    private val pointsAdapter by lazy { PointsAdapter() }

    private val binding by viewBinding<FragmentResultBinding>()
    private val viewModel by viewModels<ResultViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getData()
        observeState()
    }

    private fun initView() {
        binding.pointsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pointsAdapter
        }
    }

    private fun getData() {
        val points = arguments?.getParcelableArrayList<PointEntity>(POINTS_KEY)
        if (points != null) {
            viewModel.onEvent(ResultEvent.LoadPoints(points))
        } else {
            findNavController().popBackStack()
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                state.points?.let { points ->
                    val filteredPoints = points.filter { it.x != 0.0 || it.y != 0.0 }
                    pointsAdapter.submitList(filteredPoints)
                    binding.graphView.setPoints(filteredPoints)
                }
            }
        }
    }

    companion object {
        const val POINTS_KEY = "points"
    }
}