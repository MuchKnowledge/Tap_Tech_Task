package com.example.entity

import com.google.gson.annotations.SerializedName

data class PointResponseItem(
    @SerializedName("x") val x: Double?,
    @SerializedName("y") val y: Double?,
)