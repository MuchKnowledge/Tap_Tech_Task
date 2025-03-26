package com.example.api

import com.example.entity.PointEntity

interface PointsRepository {
    fun getPoints(count: Int): List<PointEntity>
}