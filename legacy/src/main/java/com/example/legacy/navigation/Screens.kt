package com.example.legacy.navigation

import android.content.Intent
import com.example.entity.PointEntity
import com.example.legacy.feature.result.LegacyResultActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen

object Screens {

    fun resultScreen(data: ArrayList<PointEntity>) = ActivityScreen("LegacyResultScreen") { context ->
        Intent(context, LegacyResultActivity::class.java).apply {
            putParcelableArrayListExtra(LegacyResultActivity.POINTS_KEY, data)
        }
    }
}