package com.example.tapYouTT.app.feature.main.data.repository

import com.example.tapYouTT.app.common.util.mappers.PointsMapper
import com.example.tapYouTT.app.core.api.ApiService
import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import com.example.tapYouTT.app.feature.main.domain.repository.PointsRepository
import io.reactivex.Single
import javax.inject.Inject

class PointsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PointsRepository {

    override fun getPoints(count: Int): Single<List<PointItem>> {
        return apiService.getPoints(count)
            .map { response ->
                response.points.map {
                    PointsMapper.toPointItem(it) }
            }
    }
}