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
    private var mRulerPaint: Paint? = null
    private var mRulerCount = 4
    private var mRulerWidth = 2
    private var mRulerColor = Color.WHITE
    private var isShowTopOfThumb = false

    constructor(context: Context?) : super(context!!) { init() }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) { init() }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) { init() }

    private fun init() {
        mRulerPaint = Paint()
        mRulerPaint?.color = mRulerColor
        mRulerPaint?.isAntiAlias = true
        splitTrack = false
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width <= 0 || mRulerCount <= 0) {
            return
        }
        val length =
            (width - paddingLeft - paddingRight - mRulerCount * mRulerWidth) / (mRulerCount + 1)
        val rulerTop = height / 2 - minimumHeight / 2
        val rulerBottom = rulerTop + minimumHeight
        var thumbRect: Rect? = null
        if (thumb != null) {
            thumbRect = thumb.bounds
        }
        for (i in 1..mRulerCount) {
            val rulerLeft = i * length + paddingLeft
            val rulerRight = rulerLeft + mRulerWidth
            if (!isShowTopOfThumb && thumbRect != null && rulerLeft - paddingLeft > thumbRect.left && rulerRight - paddingLeft < thumbRect.right) {
                continue
            }
            canvas.drawRect(
                rulerLeft.toFloat(),
                rulerTop.toFloat(),
                rulerRight.toFloat(),
                rulerBottom.toFloat(),
                mRulerPaint!!
            )
        }
    }

    fun setRulerCount(mRulerCount: Int) {
        this.mRulerCount = mRulerCount
        requestLayout()
    }

    fun setRulerWidth(mRulerWidth: Int) {
        this.mRulerWidth = mRulerWidth
        requestLayout()
    }

    fun setRulerColor(mRulerColor: Int) {
        this.mRulerColor = mRulerColor
        if (mRulerPaint != null) {
            mRulerPaint?.color = mRulerColor
            requestLayout()
        }
    }

    fun setShowTopOfThumb(isShowTopOfThumb: Boolean) {
        this.isShowTopOfThumb = isShowTopOfThumb
        requestLayout()
    }
}