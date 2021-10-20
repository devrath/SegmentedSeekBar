package com.example.code

import android.graphics.*
import android.graphics.drawable.Drawable
import java.util.ArrayList

class ProgressBarDrawable(sectionsList: List<Section>) : Drawable() {



    private var parts = 0
    private var paint: Paint? = null
    private val fillColor = Color.parseColor("#2D6EB9")
    private val emptyColor = Color.parseColor("#233952")
    private val separatorColor = Color.parseColor("#FFFFFF")
    private var separators: MutableList<RectF>? = null
    override fun onLevelChange(level: Int): Boolean {
        invalidateSelf()
        return true
    }

    init {
        this.parts = sectionsList.size
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        separators = ArrayList()
    }

    override fun draw(canvas: Canvas) {
        // Calculate values
        val b = bounds
        val width = b.width().toFloat()
        val height = b.height().toFloat()
        val spaceFilled = (level * width / 10000).toInt()
        val rectFill = RectF(0F, 0F, spaceFilled.toFloat(), height)
        val rectEmpty = RectF(spaceFilled.toFloat(), 0F, width, height)
        val spaceBetween = (width / 100).toInt()
        val widthPart = (width / parts - (0.9 * spaceBetween).toInt()).toInt()
        var startX = widthPart
        for (i in 0 until parts - 1) {
            separators!!.add(RectF(startX.toFloat(), 0F, (startX + spaceBetween).toFloat(), height))
            startX += spaceBetween + widthPart
        }

        paint?.let{
            // Foreground
            it.color = fillColor
            canvas.drawRect(rectFill, it)

            // Background
            it.color = emptyColor
            canvas.drawRect(rectEmpty, it)

            // Separator
            it.color = separatorColor
            for (separator in separators!!) {
                canvas.drawRect(separator, it)
            }
        }

    }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(cf: ColorFilter?) {}
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

}