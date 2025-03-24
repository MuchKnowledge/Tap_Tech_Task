package com.example.tapYouTT.app.feature.main.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.tapYouTT.R
import com.example.tapYouTT.app.App.Companion.appComponent
import com.example.tapYouTT.app.common.util.extensions.safeClick
import com.example.tapYouTT.app.core.navigation.Screens
import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    internal lateinit var provider: Provider<MainPresenter>

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    @ProvidePresenter
    internal fun provide(): MainPresenter = provider.get()

    private val navigator: Navigator = AppNavigator(this, -1)

    private lateinit var goButton: Button
    private lateinit var countEt: EditText
    private lateinit var loadingIndicator: ProgressBar
    private lateinit var loadingOverlay: View
    private lateinit var loadingContainer: View
    private lateinit var loadingText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setNavigator()
        setListeners()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        hideLoading()
    }

    override fun navigateToResult(points: List<PointItem>) {
        router.navigateTo(Screens.resultScreen(ArrayList(points)))
        hideLoading()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    private fun initView() {
        goButton = findViewById(R.id.goButton)
        countEt = findViewById(R.id.count_et)
        loadingIndicator = findViewById(R.id.loadingIndicator)
        loadingOverlay = findViewById(R.id.loadingOverlay)
        loadingContainer = findViewById(R.id.loadingContainer)
        loadingText = findViewById(R.id.loadingText)
    }

    private fun setNavigator() {
        navigatorHolder.setNavigator(navigator)
    }

    private fun setListeners() {
        goButton.safeClick {
            val count = countEt.text.toString().toIntOrNull()
            if (count in MIN_COUNT_VALUE ..  MAX_COUNT_VALUE && count != null) {
                showLoading()
                presenter.onGoClicked(count)
            } else {
                presenter.showError(getString(R.string.value_error))
            }
        }
    }

    private fun showLoading() {
        goButton.isEnabled = false
        countEt.isEnabled = false

        loadingOverlay.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).setDuration(200).start()
        }

        loadingContainer.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).setDuration(200).start()
        }
    }

    private fun hideLoading() {
        goButton.isEnabled = true
        countEt.isEnabled = true

        loadingOverlay.animate()
            .alpha(0f)
            .setDuration(200)
            .withEndAction { loadingOverlay.visibility = View.GONE }
            .start()

        loadingContainer.animate()
            .alpha(0f)
            .setDuration(200)
            .withEndAction { loadingContainer.visibility = View.GONE }
            .start()
    }

    companion object {

        const val MIN_COUNT_VALUE = 1
        const val MAX_COUNT_VALUE = 1000
    }
}