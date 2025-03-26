package com.example.legacy.feature.result

import com.example.entity.PointEntity
import moxy.MvpPresenter
import javax.inject.Inject

class LegacyResultPresenter @Inject constructor() : MvpPresenter<LegacyResultView>() {

    fun onPointsReceived(points: List<PointEntity>) {
        viewState.displayPoints(points)
    }
}