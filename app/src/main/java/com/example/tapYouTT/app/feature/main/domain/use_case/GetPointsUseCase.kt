package com.example.tapYouTT.app.feature.main.domain.use_case

import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import com.example.tapYouTT.app.feature.main.domain.repository.PointsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPointsUseCase @Inject constructor(
    private val pointsRepository: PointsRepository
) {

    operator fun invoke(count: Int): Single<List<PointItem>> {
        return pointsRepository.getPoints(count)
    }
}