package com.example.common_util

import android.app.Activity
import android.widget.Toast

fun Activity.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}