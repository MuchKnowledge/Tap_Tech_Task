package com.example.entity

import com.google.gson.annotations.SerializedName

data class PointsResponse(
   @SerializedName("points") val points: List<PointResponseItem>,
)