package com.example.code.otherseekbars.dottedseekbar

import android.content.Context
import androidx.appcompat.widget.AppCompatSeekBar
import android.graphics.Bitmap
import com.example.code.R
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import kotlin.jvm.Synchronized

class DottedSeekBar : AppCompatSeekBar {
    /** Int values which corresponds to dots  */
    private var mDotsPositions: IntArray? = null

    /** Drawable for dot  */
    private var mDotBitmap: Bitmap? = null

    constructor(context: Context?) : super(context!!) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init(attrs)
    }

    /**
     * Initializes Seek bar extended attributes from xml
     *
     * @param attributeSet [AttributeSet]
     */
    private fun init(attributeSet: AttributeSet?) {
        val attrsArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.DottedSeekBar, 0, 0)
        val dotsArrayResource =
            attrsArray.getResourceId(R.styleable.DottedSeekBar_dots_positions, 0)
        if (0 != dotsArrayResource) {
            mDotsPositions = resources.getIntArray(dotsArrayResource)
        }
        val dotDrawableId = attrsArray.getResourceId(R.styleable.DottedSeekBar_dots_drawable, 0)
        if (0 != dotDrawableId) {
            mDotBitmap = BitmapFactory.decodeResource(resources, dotDrawableId)
        }
    }

    /**
     * @param dots to be displayed on this SeekBar
     */
    fun setDots(dots: IntArray?) {
        mDotsPositions = dots
        invalidate()
    }

    /**
     * @param dotsResource resource id to be used for dots drawing
     */
    fun setDotsDrawable(dotsResource: Int) {
        mDotBitmap = BitmapFactory.decodeResource(resources, dotsResource)
        invalidate()
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = (measuredWidth - paddingLeft - paddingRight).toFloat()
        val step = width / max.toFloat()
        if (null != mDotsPositions && mDotsPositions!!.isNotEmpty() && null != mDotBitmap) {
            // draw dots if we have ones
            for (position in mDotsPositions!!) {
                canvas.drawBitmap(mDotBitmap!!, position * step, 0f, null)
            }
        }
    }
}