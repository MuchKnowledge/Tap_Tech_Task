package com.example.tapYouTT.app.feature.main.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PointsResponse(
    @SerializedName("points") val points: List<PointResponseItem>,
) : Parcelable