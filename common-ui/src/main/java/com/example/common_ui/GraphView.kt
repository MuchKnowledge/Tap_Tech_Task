package com.example.common_ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.entity.PointEntity

class GraphView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var points: List<PointEntity> = emptyList()
    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 4f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }
    private val pointPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    fun setPoints(newPoints: List<PointEntity>) {
        points = newPoints
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val pointSpacing = 100f
        val totalWidth = (points.size - 1) * pointSpacing + 100f
        setMeasuredDimension(totalWidth.toInt().coerceAtLeast(measuredWidth), measuredHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (points.isEmpty()) return

        val height = height.toFloat()
        val maxY = points.maxOf { it.y }
        val minY = points.minOf { it.y }
        val pointSpacing = 100f

        val path = Path()

        points.forEachIndexed { index, point ->
            val x = index * pointSpacing
            val y = if (maxY == minY) {
                height / 2f
            } else {
                (height - ((point.y - minY) / (maxY - minY) * height)).toFloat()
            }

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                val previousPoint = points[index - 1]
                val prevX = (index - 1) * pointSpacing
                val prevY = if (maxY == minY) {
                    height / 2f
                } else {
                    (height - ((previousPoint.y - minY) / (maxY - minY) * height)).toFloat()
                }

                val controlX1 = prevX + (x - prevX) * 0.33f
                val controlX2 = prevX + (x - prevX) * 0.66f
                path.cubicTo(controlX1, prevY, controlX2, y, x, y)
            }
            canvas.drawCircle(x, y, 8f, pointPaint)
        }

        canvas.drawPath(path, paint)
    }
}