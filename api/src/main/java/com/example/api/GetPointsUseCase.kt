package com.example.api

import com.example.entity.PointEntity

interface GetPointsUseCase {
    operator fun invoke(count: Int): List<PointEntity>
}