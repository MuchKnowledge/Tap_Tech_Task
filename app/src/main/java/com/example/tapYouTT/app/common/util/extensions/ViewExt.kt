package com.example.tapYouTT.app.common.util.extensions

import android.app.Activity
import android.os.SystemClock
import android.view.View
import android.widget.Toast

inline fun View.safeClick(crossinline listener: () -> Unit) {
    var clickTime = 0L
    val throttleTime = 1000

    setOnClickListener {
        if (SystemClock.uptimeMillis() <= (clickTime + throttleTime)) return@setOnClickListener
        clickTime = SystemClock.uptimeMillis()
        listener.invoke()
    }
}

fun Activity.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}