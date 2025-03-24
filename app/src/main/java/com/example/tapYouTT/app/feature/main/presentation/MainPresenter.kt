package com.example.tapYouTT.app.feature.main.presentation

import com.example.tapYouTT.app.feature.main.domain.use_case.GetPointsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val getPointsUseCase: GetPointsUseCase
) : MvpPresenter<MainView>() {

    private val disposables = CompositeDisposable()

    fun onButtonClicked(count: Int) {
        viewState.showLoading()

        if (count !in MIN_COUNT_VALUE..MAX_COUNT_VALUE) {
            viewState.showCountError()
            return
        }
            disposables.add(
                getPointsUseCase(count)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { points -> viewState.navigateToResult(points) },
                        { error -> viewState.showNetworkError(error.message ?: "") }
                    ))
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    companion object {

        const val MIN_COUNT_VALUE = 1
        const val MAX_COUNT_VALUE = 1000
    }
}