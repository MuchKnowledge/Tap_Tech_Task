package com.example.tapYouTT.app.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class PointsResponse(
    @SerializedName("points") val points: List<PointResponseItem>,
)