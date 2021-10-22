package com.example.code.customSeekBar.normal

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.code.R
import kotlin.math.max
import kotlin.math.min

class IndicatorProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val TAG = "IndicatorProgressBar"

    private var barColor = Color.GRAY
    private var barHeight = 25F
    private var indicatorColor = Color.CYAN
    private var progressColor = Color.GREEN
    private val paint = Paint()

    lateinit var indicatorPositions: List<Float>
    var progress = 0F // From float from 0 to 1
        set(state) {
            field = state
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.IndicatorProgressBar,
            0, 0
        ).apply {
            barColor = getColor(R.styleable.IndicatorProgressBar_barColor, barColor)
            barHeight = getFloat(R.styleable.IndicatorProgressBar_barHeight, barHeight)
            progress = getFloat(R.styleable.IndicatorProgressBar_progress, progress)
            progressColor = getColor(R.styleable.IndicatorProgressBar_progressColor, progressColor)
            indicatorColor =
                getColor(R.styleable.IndicatorProgressBar_indicatorColor, indicatorColor)
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.FILL // We will only use FILL for the progress bar's components.
        drawProgressBar(canvas)
        drawProgress(canvas)
        drawIndicators(canvas)
    }

    /**
     * Used to get the measuredWidth from the view as a float to be used in the draw methods.
     */
    private fun width(): Float {
        return measuredWidth.toFloat()
    }

    private fun drawProgressBar(canvas: Canvas) {
        paint.color = barColor
        drawCenteredBar(canvas, 0F, width())
    }

    private fun drawProgress(canvas: Canvas) {
        paint.color = progressColor

        val barWidth = (progress) * width()
        drawCenteredBar(canvas, 0F, barWidth)
    }

    private fun drawIndicators(canvas: Canvas) {
        paint.color = indicatorColor
        indicatorPositions.forEach {
            val barPositionCenter = it * width()
            val barPositionLeft = barPositionCenter - 3F
            val barPositionRight = barPositionCenter + 3F

            drawCenteredBar(canvas, barPositionLeft, barPositionRight)
        }
    }

    private fun drawCenteredBar(canvas: Canvas, left: Float, right: Float) {
        val barTop = (measuredHeight - barHeight) / 2
        val barBottom = (measuredHeight + barHeight) / 2

        val barRect = RectF(left, barTop, right, barBottom)
        canvas.drawRoundRect(barRect, 50F, 50F, paint)
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putFloat("progress", progress)
        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        var viewState = state

        if (viewState is Bundle) {
            progress = viewState.getFloat("progress", progress)
            viewState = viewState.getParcelable("superState")!!
        }

        super.onRestoreInstanceState(viewState)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)

        Log.d(TAG, "x=${event.x} / ${width()} (${event.x / measuredWidth}%), y=${event.y}")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                return updateProgress(event)
            }
            MotionEvent.ACTION_MOVE -> {
                return updateProgress(event)
            }
            MotionEvent.ACTION_UP -> {
                performClick()
                return true
            }
        }
        return false
    }

    private fun updateProgress(event: MotionEvent): Boolean {
        // percent may be outside the range (0..1)
        val percent = event.x / width()
        val boundedPercent = min(max(percent, 0F), 1F) // not above 1
        progress = boundedPercent

        invalidate() // Make the view redraw itself
        return true
    }
}