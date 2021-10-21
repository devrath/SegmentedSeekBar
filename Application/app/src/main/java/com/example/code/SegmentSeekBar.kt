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

    private var totalLengthSpan = 0


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
        if (width <= 0 || segmentsCount <= 0) { return
        }else { drawSegments(canvas) }
    }

    private fun drawSegments(
        canvas: Canvas
    ) {
        val thumbRect = prepareThumbRect()
        val rulerTop = height / 1.2 - minimumHeight / 2
        val rulerBottom = rulerTop/ 1.4 + minimumHeight /2.9

        for (i in 1..segmentsCount) {

            val length =
                (width - paddingLeft - paddingRight - segmentsCount * segmentDividerWidth) / (segmentsCount + 1)


            val rulerLeft = i * length + paddingLeft
            val rulerRight = rulerLeft + segmentDividerWidth
            if (!isShowTopOfThumb &&
                rulerLeft - paddingLeft > thumbRect.left &&
                rulerRight - paddingLeft < thumbRect.right
            ) { continue }

            canvas.drawRect(
                rulerLeft.toFloat(), rulerTop.toFloat(), rulerRight.toFloat(),
                rulerBottom.toFloat(), segmentDividerPaint!!
            )
        }
    }

    private fun prepareThumbRect(): Rect {
        return thumb.bounds
    }

    private fun setTotalLengthSpan(segmentsList: List<Segment>) {
        var sum = 0
        for(segment in segmentsList){ sum += segment.curSegmentDurationSpan }
        totalLengthSpan = sum
    }

    private fun setIndividualSpansInPercentage(segmentsList: List<Segment>) {
        for(segment in segmentsList) {
            ((segment.curSegmentDurationSpan / totalLengthSpan) * 100);
        }
    }

    private fun setSegmentDividerColor(segmentDividerColor: Int) {
        this.segmentDividerColor = segmentDividerColor
        if (segmentDividerPaint != null) {
            segmentDividerPaint?.color = segmentDividerColor
        }
    }

    fun setMplSegmentStyle(segmentsList: List<Segment>,
                           segmentDividerColor:Int,segmentDividerWidth: Int
    ) {
        this.segmentsCount = segmentsList.size
        this.segmentDividerWidth = segmentDividerWidth
        setTotalLengthSpan(segmentsList)
        setIndividualSpansInPercentage(segmentsList)
        setSegmentDividerColor(segmentDividerColor)
        requestLayout()
    }
}