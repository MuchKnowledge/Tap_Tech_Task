package com.example.tapYouTT.app.feature.main.presentation

import com.example.tapYouTT.app.feature.main.domain.use_case.GetPointsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val getPointsUseCase: GetPointsUseCase
) : MvpPresenter<MainView>() {

    fun onGoClicked(count: Int) {
        getPointsUseCase(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { points -> viewState.navigateToResult(points) },
                { error -> viewState.showError(error.message ?: "Error") }
            )
    }

    fun showError(text: String) {
        viewState.showError(text)
    }
}