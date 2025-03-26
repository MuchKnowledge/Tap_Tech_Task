package com.example.modern.feature.main.data

import com.example.api.ApiService
import com.example.api.PointsRepository
import com.example.entity.PointEntity
import javax.inject.Inject

class PointsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PointsRepository {

    override fun getPoints(count: Int): List<PointEntity> {
        return try {
            val response = apiService.getPoints(count).execute()
            if (response.isSuccessful) {
                response.body()?.points?.mapNotNull {
                    PointsMapper.toPointEntity(it)
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}