package com.example.legacy.feature.main.presentation

import com.example.legacy.feature.main.domain.LegacyGetPointsUseCaseImpl
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class LegacyMainPresenter @Inject constructor(
    private val legacyGetPointsUseCaseImpl: LegacyGetPointsUseCaseImpl
) : MvpPresenter<LegacyMainView>() {

    private var disposable: Disposable? = null

    fun onButtonClicked(count: Int) {
        viewState.showLoading()

        if (count in MIN_COUNT_VALUE..MAX_COUNT_VALUE) fetchData(count)
        else viewState.showCountError()
    }

    private fun fetchData(count: Int) {
        disposable?.dispose()
        disposable = Single.fromCallable { legacyGetPointsUseCaseImpl(count) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { points -> viewState.navigateToResult(points) },
                { error -> viewState.showNetworkError(error.message ?: "") }
            )
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    companion object {

        const val MIN_COUNT_VALUE = 1
        const val MAX_COUNT_VALUE = 1000
    }
}