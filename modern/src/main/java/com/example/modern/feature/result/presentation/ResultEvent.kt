package com.example.modern.feature.result.presentation

import com.example.entity.PointEntity

sealed class ResultEvent {
    data class LoadPoints(val points: List<PointEntity>) : ResultEvent()
}