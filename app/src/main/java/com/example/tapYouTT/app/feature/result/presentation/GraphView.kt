package com.example.tapYouTT.app.feature.result.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import com.example.tapYouTT.app.feature.main.domain.model.PointItem

class GraphView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var points: List<PointItem> = emptyList()
    private var scale: Float = 1f
    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 4f
        style = Paint.Style.STROKE
    }
    private val pointPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private val scaleGestureDetector =
        ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scale *= detector.scaleFactor
                scale = scale.coerceIn(0.5f, 3f)
                invalidate()
                return true
            }
        })

    fun setPoints(newPoints: List<PointItem>) {
        points = newPoints
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return scaleGestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val pointSpacing = 100f
        val totalWidth = (points.size - 1) * pointSpacing * scale + 100f //
        setMeasuredDimension(totalWidth.toInt().coerceAtLeast(measuredWidth), measuredHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (points.isEmpty()) return

        val height = height.toFloat()
        val maxY = points.maxOf { it.y }
        val minY = points.minOf { it.y }
        val pointSpacing = 100f

        val path = android.graphics.Path()
        var first = true

        points.forEachIndexed { index, point ->
            val x = index * pointSpacing * scale
            val y = if (maxY == minY) {
                height / 2f
            } else {
                (height - ((point.y - minY) / (maxY - minY) * height * scale)).toFloat()
            }

            if (first) {
                path.moveTo(x, y)
                first = false
            } else {
                path.lineTo(x, y)
            }
            canvas.drawCircle(x, y, 8f * scale, pointPaint)
        }

        canvas.drawPath(path, paint)
    }
}