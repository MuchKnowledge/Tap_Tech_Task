package com.example.tapYouTT.app.feature.result.presentation

import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.tapYouTT.R
import com.example.tapYouTT.app.App.Companion.appComponent
import com.example.tapYouTT.app.feature.main.domain.model.PointItem
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class ResultActivity : MvpAppCompatActivity(), ResultView {

    @Inject
    lateinit var router: Router

    @Inject
    internal lateinit var provider: Provider<ResultPresenter>

    @InjectPresenter
    internal lateinit var presenter: ResultPresenter

    @ProvidePresenter
    internal fun provide(): ResultPresenter = provider.get()

    private lateinit var pointsTable: TableLayout
    private lateinit var graphView: GraphView

    companion object {
        const val POINTS_KEY = "points_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initView()
        getData()
    }

    override fun displayPoints(points: List<PointItem>) {
        pointsTable.removeAllViews()
        val filteredPoints = points.filter { it.x != 0.0 || it.y != 0.0 }
        filteredPoints.forEachIndexed { index, point ->
            val row = TableRow(this).apply {
                addView(createCell(point.x.toString()))
                addView(createCell(point.y.toString()))
            }
            pointsTable.addView(row)
        }
        graphView.setPoints(filteredPoints)
    }

    private fun initView() {
        pointsTable = findViewById(R.id.pointsTable)
        graphView = findViewById(R.id.graphView)
        pointsTable.setColumnStretchable(0, true)
        pointsTable.setColumnStretchable(1, true)
    }

    private fun getData() {
        val points = intent.getParcelableArrayListExtra<PointItem>(POINTS_KEY)
        if (points != null) {
            presenter.onPointsReceived(points)
        } else {
            finish()
        }
    }

    private fun createCell(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            setPadding(8, 8, 8, 8)
            gravity = Gravity.CENTER
            setBackgroundResource(R.drawable.cell_border)
            setTextColor(resources.getColor(android.R.color.black))
        }
    }
}