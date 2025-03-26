package com.example.modern.feature.result.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.entity.PointEntity
import com.example.common_ui.PointDiffCallback
import com.example.tapYou.databinding.ResultItemPointBinding

class PointsAdapter : RecyclerView.Adapter<PointsAdapter.PointViewHolder>() {

    private var points: List<PointEntity> = emptyList()

    fun submitList(newPoints: List<PointEntity>) {
        val diffResult = DiffUtil.calculateDiff(PointDiffCallback(points, newPoints))
        points = newPoints
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val binding =
            ResultItemPointBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(points[position])
    }

    override fun getItemCount(): Int = points.size

    class PointViewHolder(private val binding: ResultItemPointBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(point: PointEntity) {
            binding.xText.text = point.x.toString()
            binding.yText.text = point.y.toString()
        }
    }
}