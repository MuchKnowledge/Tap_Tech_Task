package com.example.legacy.feature.result

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common_ui.GraphView
import com.example.entity.PointEntity
import com.example.legacy.LegacyApp.Companion.appComponent
import com.example.tapYouLegacy.R
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class LegacyResultActivity : MvpAppCompatActivity(), LegacyResultView {

    @Inject
    internal lateinit var provider: Provider<LegacyResultPresenter>

    @InjectPresenter
    internal lateinit var presenter: LegacyResultPresenter

    @ProvidePresenter
    internal fun provide(): LegacyResultPresenter = provider.get()

    private lateinit var legacyPointsAdapter: LegacyPointsAdapter
    private lateinit var pointsRecyclerView: RecyclerView

    private lateinit var graphView: GraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initView()
        getData()
    }

    override fun displayPoints(points: List<PointEntity>) {
        val filteredPoints = points.filter { it.x != 0.0 || it.y != 0.0 }
        legacyPointsAdapter.submitList(filteredPoints)
        graphView.setPoints(filteredPoints)
    }

    private fun initView() {
        pointsRecyclerView = findViewById(R.id.points_recycler_vew)
        graphView = findViewById(R.id.graph_view)

        legacyPointsAdapter = LegacyPointsAdapter()
        pointsRecyclerView.layoutManager = LinearLayoutManager(this)
        pointsRecyclerView.adapter = legacyPointsAdapter
    }

    private fun getData() {
        val points = intent.getParcelableArrayListExtra<PointEntity>(POINTS_KEY)
        if (points != null) {
            presenter.onPointsReceived(points)
        } else {
            finish()
        }
    }

    companion object {
        const val POINTS_KEY = "points_key"
    }
}