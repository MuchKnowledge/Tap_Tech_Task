package com.example.tapYouTT.app.common.navigation

import android.content.Intent
import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import com.example.tapYouTT.app.feature.result.presentation.ResultActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen

object Screens {

    fun resultScreen(data: ArrayList<PointItem>) = ActivityScreen("ResultScreen") { context ->
        Intent(context, ResultActivity::class.java).apply {
            putParcelableArrayListExtra(ResultActivity.POINTS_KEY, data)
        }
    }
}