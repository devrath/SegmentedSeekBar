package com.example.code.segmentedSeekBar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.code.R

class SeekBarFrame : FrameLayout {

    private var segmentPaint: Paint? = null


    constructor(context: Context?) : super(context!!) { init(context) }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) { init(context) }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) { init(context) }

    private fun init(context: Context) {
        prepareSegmentPaint(context)
    }


    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        segmentPaint?.apply {
            canvas.drawLine(0F,
                (height / 2).toFloat(),
                width.toFloat(),
                (height / 2).toFloat(),
                this)
        }
    }


    private fun prepareSegmentPaint(context: Context) {
        segmentPaint = Paint()
        segmentPaint?.apply {
            strokeWidth = 5F
            segmentPaint?.color = ContextCompat.getColor(context, R.color.green)
            //segmentPaint?.isAntiAlias = true
        }

    }

}