package com.example.tapYouTT.app.feature.main.data.mappers

import com.example.tapYouTT.app.feature.main.data.model.PointResponseItem
import com.example.tapYouTT.app.feature.main.domain.model.PointItem

object PointsMapper {

    fun toPointItem(point: PointResponseItem): PointItem {
        return PointItem(
            x = point.x ?: 0.0,
            y = point.y ?: 0.0,
        )
    }
}