package com.example.tapYouTT.app.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class PointResponseItem(
    @SerializedName("x") val x: Double?,
    @SerializedName("y") val y: Double?,
)