package com.example.common_util

import android.os.SystemClock
import android.view.View

inline fun View.safeClick(crossinline listener: () -> Unit) {
    var clickTime = 0L
    val throttleTime = 1000

    setOnClickListener {
        if (SystemClock.uptimeMillis() <= (clickTime + throttleTime)) return@setOnClickListener
        clickTime = SystemClock.uptimeMillis()
        listener.invoke()
    }
}