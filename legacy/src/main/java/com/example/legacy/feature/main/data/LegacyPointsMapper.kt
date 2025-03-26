package com.example.legacy.feature.main.data

import com.example.entity.PointEntity
import com.example.entity.PointResponseItem

object LegacyPointsMapper {

    fun toPointEntity(point: PointResponseItem): PointEntity {
        return PointEntity(
            x = point.x ?: 0.0,
            y = point.y ?: 0.0,
        )
    }
}