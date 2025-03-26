package com.example.api

import com.example.entity.PointsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/test/points")
    fun getPoints(@Query("count") count: Int): Call<PointsResponse>
}