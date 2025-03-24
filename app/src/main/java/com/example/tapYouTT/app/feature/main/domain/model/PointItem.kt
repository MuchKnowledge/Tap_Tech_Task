package com.example.tapYouTT.app.feature.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PointItem(
    val x: Double,
    val y: Double,
) : Parcelable