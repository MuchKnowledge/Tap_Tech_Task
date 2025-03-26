package com.example.legacy.feature.result

import com.example.entity.PointEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LegacyResultView : MvpView {

    fun displayPoints(points: List<PointEntity>)
}