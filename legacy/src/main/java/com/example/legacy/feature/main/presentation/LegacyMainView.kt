package com.example.legacy.feature.main.presentation

import com.example.entity.PointEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LegacyMainView : MvpView {

    fun showCountError()
    fun showNetworkError(message: String)
    fun navigateToResult(points: List<PointEntity>)
    fun showLoading()
    fun hideLoading()
}