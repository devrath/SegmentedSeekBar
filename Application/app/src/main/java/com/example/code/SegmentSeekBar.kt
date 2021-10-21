package com.example.code

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import kotlin.jvm.Synchronized

class SegmentSeekBar : AppCompatSeekBar {
    private var segmentDividerPaint: Paint? = null
    private var segmentsCount = 0
    private var segmentDividerWidth = 3
    private var segmentDividerColor = Color.WHITE
    private var isShowTopOfThumb = false

    constructor(context: Context?) : super(context!!) { init() }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) { init() }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) { init() }

    private fun init() {
        segmentDividerPaint = Paint()
        segmentDividerPaint?.color = segmentDividerColor
        segmentDividerPaint?.isAntiAlias = true
        splitTrack = false
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (width <= 0 || segmentsCount <= 0) { return }

        val length =
            (width - paddingLeft - paddingRight - segmentsCount * segmentDividerWidth) / (segmentsCount + 1)
        val rulerTop = height / 1.2 - minimumHeight / 2
        val rulerBottom = rulerTop/ 1.4 + minimumHeight /2.9

        var thumbRect: Rect? = null
        if (thumb != null) {
            thumbRect = thumb.bounds
        }

        for (i in 1..segmentsCount) {
            val rulerLeft = i * length + paddingLeft
            val rulerRight = rulerLeft + segmentDividerWidth
            if (!isShowTopOfThumb &&
                thumbRect != null &&
                rulerLeft - paddingLeft > thumbRect.left &&
                rulerRight - paddingLeft < thumbRect.right) {

                continue

            }
            canvas.drawRect(rulerLeft.toFloat(), rulerTop.toFloat(), rulerRight.toFloat(),
                rulerBottom.toFloat(), segmentDividerPaint!!
            )
        }
    }

    fun setMplSegmentStyle(segmentsList: List<Segment>,
                           segmentDividerColor:Int,segmentDividerWidth: Int
    ) {
        setSegmentsCount(segmentsList.size)
        setSegmentDividerColor(segmentDividerColor)
        setSegmentDividerWidth(segmentDividerWidth)
        requestLayout()
    }

    private fun setSegmentsCount(mRulerCount: Int) { this.segmentsCount = mRulerCount }

    private fun setSegmentDividerColor(segmentDividerColor: Int) {
        this.segmentDividerColor = segmentDividerColor
        if (segmentDividerPaint != null) {
            segmentDividerPaint?.color = segmentDividerColor
        }
    }

    private fun setSegmentDividerWidth(segmentDividerWidth: Int) {
        this.segmentDividerWidth = segmentDividerWidth
    }


}