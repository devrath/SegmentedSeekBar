package com.example.code

import android.graphics.*
import android.graphics.drawable.Drawable

class ProgressBarDrawable(
    sectionsList: List<Section>,
    private val seekPlayedColor: Int, private val seekUnPlayedColor: Int
) : Drawable() {

    companion object {
        private const val DEFAULT_NUM_SEGMENTS = 0
    }

    private var numOfSegments = DEFAULT_NUM_SEGMENTS
    private val mPaint = Paint()
    private val mSegment = RectF()


    init {
        numOfSegments = sectionsList.size
    }


    override fun onLevelChange(level: Int): Boolean {
        invalidateSelf()
        return true
    }

    override fun draw(canvas: Canvas) {
        val level = level / 10000f
        val b = bounds
        val gapWidth = b.height() / 4f
        val segmentWidth = (b.width() - (numOfSegments - 1) * gapWidth) / numOfSegments
        mSegment[0f, 0f, segmentWidth] = 10f
        mPaint.color = seekPlayedColor
        for (i in 0 until numOfSegments) {
            val loLevel = i / numOfSegments.toFloat()
            val hiLevel = (i + 1) / numOfSegments.toFloat()
            if (level in loLevel..hiLevel) {
                val middle = mSegment.left + numOfSegments * segmentWidth * (level - loLevel)
                canvas.drawRect(mSegment.left, mSegment.top, middle, mSegment.bottom, mPaint)
                mPaint.color = seekUnPlayedColor
                canvas.drawRect(middle, mSegment.top, mSegment.right, mSegment.bottom, mPaint)
            } else {
                canvas.drawRect(mSegment, mPaint)
            }
            mSegment.offset(mSegment.width() + gapWidth, 0f)
        }
    }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(cf: ColorFilter?) {}
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

}