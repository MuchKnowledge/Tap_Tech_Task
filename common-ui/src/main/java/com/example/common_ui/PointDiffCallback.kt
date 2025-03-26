package com.example.common_ui

import androidx.recyclerview.widget.DiffUtil
import com.example.entity.PointEntity

class PointDiffCallback(
    private val oldList: List<PointEntity>,
    private val newList: List<PointEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPoint = oldList[oldItemPosition]
        val newPoint = newList[newItemPosition]
        return oldPoint.x == newPoint.x && oldPoint.y == newPoint.y
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPoint = oldList[oldItemPosition]
        val newPoint = newList[newItemPosition]
        return oldPoint == newPoint
    }
}