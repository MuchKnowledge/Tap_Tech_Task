package com.example.tapYouTT.app.feature.main.domain.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class PointItem(
    val x: Double,
    val y: Double,
) : Parcelable