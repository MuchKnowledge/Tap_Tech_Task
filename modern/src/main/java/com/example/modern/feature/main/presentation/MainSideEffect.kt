package com.example.modern.feature.main.presentation

import com.example.entity.PointEntity

sealed class MainSideEffect {
    object ShowCountError : MainSideEffect()
    data class ShowNetworkError(val message: String) : MainSideEffect()
    data class NavigateToResult(val points: List<PointEntity>) : MainSideEffect()
}