package com.example.tapYouTT.app.feature.result.presentation

import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ResultView : MvpView {

    fun displayPoints(points: List<PointItem>)
}