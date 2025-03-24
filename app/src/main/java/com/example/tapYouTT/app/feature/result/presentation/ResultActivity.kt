package com.example.tapYouTT.app.feature.result.presentation

import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tapYouTT.R
import com.example.tapYouTT.app.App.Companion.appComponent
import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class ResultActivity : MvpAppCompatActivity(), ResultView {

    @Inject
    internal lateinit var provider: Provider<ResultPresenter>

    @InjectPresenter
    internal lateinit var presenter: ResultPresenter

    @ProvidePresenter
    internal fun provide(): ResultPresenter = provider.get()

    private lateinit var pointsAdapter: PointsAdapter
    private lateinit var pointsRecyclerView: RecyclerView

    private lateinit var graphView: GraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initView()
        getData()
    }

    override fun displayPoints(points: List<PointItem>) {
        val filteredPoints = points.filter { it.x != 0.0 || it.y != 0.0 }
        pointsAdapter.setPoints(filteredPoints)
        graphView.setPoints(filteredPoints)
    }

    private fun initView() {
        pointsRecyclerView = findViewById(R.id.pointsRecyclerView)
        graphView = findViewById(R.id.graph_view)

        pointsAdapter = PointsAdapter()
        pointsRecyclerView.layoutManager = LinearLayoutManager(this)
        pointsRecyclerView.adapter = pointsAdapter
    }

    private fun getData() {
        val points = intent.getParcelableArrayListExtra<PointItem>(POINTS_KEY)
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