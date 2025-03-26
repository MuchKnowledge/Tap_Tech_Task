package com.example.legacy.feature.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common_ui.PointDiffCallback
import com.example.entity.PointEntity
import com.example.tapYouLegacy.R

class LegacyPointsAdapter : RecyclerView.Adapter<LegacyPointsAdapter.PointViewHolder>() {

    private var points: List<PointEntity> = emptyList()

    fun submitList(newPoints: List<PointEntity>) {
        val diffResult = DiffUtil.calculateDiff(PointDiffCallback(points, newPoints))
        points = newPoints
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.result_item_point, parent, false)
        return PointViewHolder(view)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(points[position])
    }

    override fun getItemCount(): Int = points.size

    inner class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val xTextView: TextView = itemView.findViewById(R.id.xTextView)
        private val yTextView: TextView = itemView.findViewById(R.id.yTextView)

        fun bind(point: PointEntity) {
            xTextView.text = point.x.toString()
            yTextView.text = point.y.toString()
        }
    }
}