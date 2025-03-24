package com.example.tapYouTT.app.feature.result.presentation

import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import moxy.MvpPresenter
import javax.inject.Inject

class ResultPresenter @Inject constructor() : MvpPresenter<ResultView>() {

    fun onPointsReceived(points: List<PointItem>) {
        viewState.displayPoints(points)
    }
}