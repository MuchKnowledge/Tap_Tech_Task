package com.example.tapYouTT.app.feature.result.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tapYouTT.R
import com.example.tapYouTT.app.feature.main.domain.model.PointItem

class PointsAdapter : RecyclerView.Adapter<PointsAdapter.PointViewHolder>() {

    private var points: List<PointItem> = emptyList()

    fun setPoints(newPoints: List<PointItem>) {
        points = newPoints
        notifyDataSetChanged()
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

        fun bind(point: PointItem) {
            xTextView.text = point.x.toString()
            yTextView.text = point.y.toString()
        }
    }
}