package com.example.modern.feature.main.presentation

import androidx.lifecycle.ViewModel
import com.example.api.GetPointsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPointsUseCase: GetPointsUseCase
) : ViewModel(), ContainerHost<MainState, MainSideEffect> {

    override val container: Container<MainState, MainSideEffect> = container(MainState())

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ButtonClick -> onButtonClick(event.count)
        }
    }

    private fun onButtonClick(count: Int) = intent {
        if (count !in MIN_COUNT_VALUE..MAX_COUNT_VALUE) {
            postSideEffect(MainSideEffect.ShowCountError)
            return@intent
        }

        reduce { state.copy(isLoading = true) }

        runCatching { getPointsUseCase(count) }
            .onSuccess { points ->
                postSideEffect(MainSideEffect.NavigateToResult(points))
            }
            .onFailure { error ->
                postSideEffect(MainSideEffect.ShowNetworkError(error.message ?: ""))
            }
    }

    companion object {
        const val MIN_COUNT_VALUE = 1
        const val MAX_COUNT_VALUE = 1000
    }
}