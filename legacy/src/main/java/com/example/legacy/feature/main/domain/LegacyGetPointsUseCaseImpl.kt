package com.example.legacy.feature.main.domain

import com.example.api.GetPointsUseCase
import com.example.api.PointsRepository
import com.example.entity.PointEntity
import javax.inject.Inject

class LegacyGetPointsUseCaseImpl @Inject constructor(
    private val pointsRepository: PointsRepository
) : GetPointsUseCase {

    override operator fun invoke(count: Int): List<PointEntity> {
        if (count <= 0) throw IllegalStateException("IllegalState")

        return pointsRepository.getPoints(count)
    }
}