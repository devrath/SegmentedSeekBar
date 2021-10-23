package com.example.code.customSeekBar.modified

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import com.example.code.R
class IndicatorProgressBarModified : AppCompatSeekBar {
    lateinit var indicatorPositions: List<Float>

    /******************** eee **********************************/
    private var barColor = ContextCompat.getColor(context, R.color.gb_seek_bar_unplayed)
    private var indicatorColor = ContextCompat.getColor(context, R.color.screen_background)
    private var progressColor = ContextCompat.getColor(context, R.color.gb_seek_bar_played)
    private val paint = Paint()


    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) { initilize(attrs) }
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int)
            : super(context!!, attrs, defStyle) { initilize(attrs) }


    private var progress = 0F // From float from 0 to 1
        set(state) {
            field = state
            invalidate()
        }

    private fun initilize(attrs: AttributeSet?) {
        paint.isAntiAlias = true
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.FILL // We will only use FILL for the progress bar's components.
        drawProgressBar(canvas)
        //drawProgress(canvas)
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
        //drawCenteredBar(canvas, 0F, width())
        drawCenteredBar(canvas, 32F, width()-32)
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
            val barPositionLeft = barPositionCenter - 5F
            val barPositionRight = barPositionCenter + 5F

            drawCenteredBar(canvas, barPositionLeft, barPositionRight)
        }
    }

    private fun drawCenteredBar(canvas: Canvas, left: Float, right: Float) {
        //val barTop = (measuredHeight - barHeight) / 2
        //val barBottom = (measuredHeight + barHeight) / 2
        val barTop = height / 1.8 - minimumHeight / 2
        val barBottom = barTop / 1.3 + minimumHeight / 2

        val barRect = RectF(left, barTop.toFloat(), right, barBottom.toFloat())
        canvas.drawRoundRect(barRect, 50F, 50F, paint)
    }

}