package com.example.modern.feature.main.presentation

sealed class MainEvent {
    data class ButtonClick(val count: Int) : MainEvent()
}