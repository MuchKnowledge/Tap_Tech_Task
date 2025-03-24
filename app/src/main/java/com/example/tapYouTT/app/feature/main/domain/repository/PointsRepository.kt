package com.example.tapYouTT.app.feature.main.domain.repository

import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import io.reactivex.Single

interface PointsRepository {
    fun getPoints(count: Int): Single<List<PointItem>>
}