package com.example.modern.feature.result.presentation

import androidx.lifecycle.ViewModel
import com.example.entity.PointEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor() : ViewModel(), ContainerHost<ResultState, Nothing> {

    override val container: Container<ResultState, Nothing> = container(ResultState())

    val state = container.stateFlow

    fun onEvent(event: ResultEvent) {
        when (event) {
            is ResultEvent.LoadPoints -> loadPoints(event.points)
        }
    }

    private fun loadPoints(points: List<PointEntity>) = intent {
        reduce {
            state.copy(points = points)
        }
    }
}