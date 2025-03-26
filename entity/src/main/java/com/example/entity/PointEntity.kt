package com.example.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PointEntity(
    val x: Double,
    val y: Double,
) : Parcelable