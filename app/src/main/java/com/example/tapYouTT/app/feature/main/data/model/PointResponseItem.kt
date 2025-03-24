package com.example.tapYouTT.app.feature.main.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PointResponseItem(
    @SerializedName("x") val x: Double?,
    @SerializedName("y") val y: Double?,
) : Parcelable