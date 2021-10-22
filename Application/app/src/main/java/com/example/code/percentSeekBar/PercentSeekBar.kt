package com.example.code.percentSeekBar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import java.util.ArrayList
import kotlin.jvm.Synchronized

class PercentSeekBar : AppCompatSeekBar {

    private var mProgressItemsList: ArrayList<ProgressItem>? = null

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle)

    fun initData(progressItemsList: ArrayList<ProgressItem>) {
        mProgressItemsList = progressItemsList
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {

        mProgressItemsList?.let { progressList ->
            if (progressList.size > 0) {

                val seekBarWidth = width
                val seekBarHeight = height
                val thumboffset = thumbOffset

                var lastProgressX = 0
                var progressItemRight: Int

                for (i in progressList.indices) {

                    val progressItem = progressList[i]
                    val progressItemWidth = currentProgressWidth(progressItem, seekBarWidth)
                    val progressPaint = currentProgressPaint(progressItem.color)

                    progressItemRight = lastProgressX + progressItemWidth

                    // for last item give right to progress item to the width
                    if (i == progressList.size - 1
                        && progressItemRight != seekBarWidth
                    ) {
                        progressItemRight = seekBarWidth
                    }

                    drawProgress(lastProgressX, thumboffset, progressItemRight, seekBarHeight,
                        canvas, progressPaint)

                    lastProgressX = progressItemRight
                }
                super.onDraw(canvas)
            }
        }
    }

    private fun drawProgress(lastProgressX: Int, thumboffset: Int, progressItemRight: Int,
        seekBarHeight: Int, canvas: Canvas, progressPaint: Paint
    ) {
        val progressRect = Rect()
        progressRect[lastProgressX, thumboffset / 2, progressItemRight] = seekBarHeight - thumboffset / 2
        canvas.drawRect(progressRect, progressPaint)
    }

    private fun currentProgressWidth(
        progressItem: ProgressItem,
        seekBarWidth: Int
    ) = (progressItem.progressItemPercentage * seekBarWidth / 100).toInt()

    private fun currentProgressPaint(color: Int): Paint {
        val currentPaint = Paint()
        currentPaint.color= ContextCompat.getColor(context, color)
        return currentPaint
    }
}