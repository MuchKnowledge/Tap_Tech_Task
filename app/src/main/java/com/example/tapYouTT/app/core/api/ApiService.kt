package com.example.tapYouTT.app.core.api

import com.example.tapYouTT.app.feature.main.data.model.PointsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("api/test/points")
    fun getPoints(@Query("count") count: Int): Single<PointsResponse>
}