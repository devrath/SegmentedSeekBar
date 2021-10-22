package com.example.code.percentSeekbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import com.example.code.R
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
                drawSections(progressList, canvas,true)
                super.onDraw(canvas)
                //drawSections(progressList, canvas,false)
            }
        }
    }

    private fun drawSections(
        progressList: ArrayList<ProgressItem>,
        canvas: Canvas,
        isPreviousProgress: Boolean
    ) {
        val seekBarWidth = width
        val seekBarHeight = height
        val thumboffset = thumbOffset

        var lastProgressX = 0
        var progressItemRight: Int

        val rulerTop = height / 1.9 - minimumHeight / 2
        val rulerBottom = rulerTop / 1.1 + minimumHeight / 2


        for (i in progressList.indices) {

            val progressItem = progressList[i]
            val progressItemWidth = currentProgressWidth(progressItem, seekBarWidth)


            val progressPaint = currentProgressPaint(progressItem,isPreviousProgress)

            progressItemRight = lastProgressX + progressItemWidth

            // for last item give right to progress item to the width
            if (i == progressList.size - 1
                && progressItemRight != seekBarWidth
            ) {
                progressItemRight = seekBarWidth
            }

            drawProgress(
                canvas, lastProgressX, rulerTop, progressItemRight,
                rulerBottom, progressPaint
            )

            lastProgressX = progressItemRight
        }
    }

    private fun drawProgress(
        canvas: Canvas, lastProgressX: Int, rulerTop: Double, progressItemRight: Int,
        rulerBottom: Double, progressPaint: Paint
    ) {
        canvas.drawRect(
            lastProgressX.toFloat(), rulerTop.toFloat(), progressItemRight.toFloat(),
            rulerBottom.toFloat(), progressPaint
        )
    }


    private fun currentProgressWidth(progressItem: ProgressItem,
                                     seekBarWidth: Int
    ) = (progressItem.progressItemPercentage * seekBarWidth / 100).toInt()

    private fun currentProgressPaint(progressItem: ProgressItem, isPreviousProgress: Boolean): Paint {
        val currentPaint = Paint()
        currentPaint.strokeWidth
        if(isPreviousProgress){
            currentPaint.color= ContextCompat.getColor(context, progressItem.color)
        }else{
            if(progressItem.isDivider){
                currentPaint.color= ContextCompat.getColor(context, progressItem.color)
            }else{
                currentPaint.color= ContextCompat.getColor(context, R.color.gb_seek_bar_played)
            }
        }
        return currentPaint
    }
}