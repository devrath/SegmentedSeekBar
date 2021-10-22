package com.example.code.customSeekBar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import com.example.code.percentSeekBar.ProgressItem
import java.util.ArrayList
import kotlin.jvm.Synchronized

class CustomSeekBar : AppCompatSeekBar {
    private var mProgressItemsList: ArrayList<ProgressItem>? = null

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle)

    fun initData(progressItemsList: ArrayList<ProgressItem>?) {
        mProgressItemsList = progressItemsList
    }

    @Synchronized
    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        if (mProgressItemsList!!.size > 0) {

            val progressBarWidth = width
            val progressBarHeight = height
            val thumboffset = thumbOffset
            var lastProgressX = 0
            var progressItemWidth: Int
            var progressItemRight: Int

            for (i in mProgressItemsList!!.indices) {
                // Item
                val progressItem = mProgressItemsList!![i]
                // Color of progress
                val progressPaint = Paint()
                progressPaint.color = resources.getColor(progressItem.color)

                progressItemWidth = (progressItem.progressItemPercentage * progressBarWidth / 100).toInt()
                progressItemRight = lastProgressX + progressItemWidth

                // for last item give right to progress item to the width
                if (i == mProgressItemsList!!.size - 1
                    && progressItemRight != progressBarWidth
                ) {
                    progressItemRight = progressBarWidth
                }
                val progressRect = Rect()
                progressRect[lastProgressX, thumboffset / 2, progressItemRight] = progressBarHeight - thumboffset / 2
                canvas.drawRect(progressRect, progressPaint)
                lastProgressX = progressItemRight
            }
            super.onDraw(canvas)
        }
    }
}