package com.example.legacy.feature.main.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.common_util.safeClick
import com.example.common_util.shortToast
import com.example.entity.PointEntity
import com.example.legacy.LegacyApp.Companion.appComponent
import com.example.legacy.navigation.Screens
import com.example.tapYouLegacy.R
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class LegacyMainActivity : MvpAppCompatActivity(), LegacyMainView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    internal lateinit var provider: Provider<LegacyMainPresenter>

    @InjectPresenter
    internal lateinit var presenter: LegacyMainPresenter

    @ProvidePresenter
    internal fun provide(): LegacyMainPresenter = provider.get()

    private val navigator: Navigator = AppNavigator(this, -1)

    private lateinit var goButton: Button
    private lateinit var countEdit: EditText
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

    override fun showCountError() {
        shortToast(getString(R.string.main_screen_value_error))
        hideLoading()
    }

    override fun showNetworkError(error: String) {
        shortToast(error)
        finish()
    }

    override fun navigateToResult(points: List<PointEntity>) {
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
        goButton = findViewById(R.id.go_button)
        countEdit = findViewById(R.id.count_edit)
        loadingIndicator = findViewById(R.id.loading_indicator)
        loadingOverlay = findViewById(R.id.loading_overlay)
        loadingContainer = findViewById(R.id.loading_container)
        loadingText = findViewById(R.id.loading_text)
    }

    private fun setNavigator() {
        navigatorHolder.setNavigator(navigator)
    }

    private fun setListeners() {
        goButton.safeClick {
            val countText = countEdit.text.toString().toIntOrNull() ?: 0
            presenter.onButtonClicked(countText)
        }
    }

    override fun showLoading() {
        goButton.isEnabled = false
        countEdit.isEnabled = false

        loadingOverlay.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).setDuration(ANIMATION_DURATION).start()
        }

        loadingContainer.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).setDuration(ANIMATION_DURATION).start()
        }
    }

    override fun hideLoading() {
        goButton.isEnabled = true
        countEdit.isEnabled = true

        loadingOverlay.animate()
            .alpha(0f)
            .setDuration(ANIMATION_DURATION)
            .withEndAction { loadingOverlay.visibility = View.GONE }
            .start()

        loadingContainer.animate()
            .alpha(0f)
            .setDuration(ANIMATION_DURATION)
            .withEndAction { loadingContainer.visibility = View.GONE }
            .start()
    }

    companion object {
        const val ANIMATION_DURATION = 200L
    }
}