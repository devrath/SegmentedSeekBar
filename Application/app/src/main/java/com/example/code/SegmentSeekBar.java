package com.example.code;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatSeekBar;

public class SegmentSeekBar extends AppCompatSeekBar {

    private Paint mRulerPaint;
    private int mRulerCount = 4;
    private int mRulerWidth = 2;
    private int mRulerColor = Color.WHITE;
    private boolean isShowTopOfThumb = false;


    public SegmentSeekBar(Context context) {
        super(context);
        init();
    }

    public SegmentSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SegmentSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRulerPaint = new Paint();
        mRulerPaint.setColor(mRulerColor);
        mRulerPaint.setAntiAlias(true);
        setSplitTrack(false);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getWidth() <= 0 || mRulerCount <= 0) {
            return;
        }

        int length = (getWidth() - getPaddingLeft() - getPaddingRight() - mRulerCount * mRulerWidth) / (mRulerCount + 1);

        int rulerTop = getHeight() / 2 - getMinimumHeight() / 2;
        int rulerBottom = rulerTop + getMinimumHeight();

        Rect thumbRect = null;
        if (getThumb() != null) {
            thumbRect = getThumb().getBounds();
        }

        for (int i = 1; i <= mRulerCount; i++) {
            int rulerLeft = i * length + getPaddingLeft();
            int rulerRight = rulerLeft + mRulerWidth;

            if (!isShowTopOfThumb && thumbRect != null && rulerLeft - getPaddingLeft() > thumbRect.left && rulerRight - getPaddingLeft() < thumbRect.right) {
                continue;
            }

            canvas.drawRect(rulerLeft, rulerTop, rulerRight, rulerBottom, mRulerPaint);
        }
    }

    public void setRulerCount(int mRulerCount) {
        this.mRulerCount = mRulerCount;
        requestLayout();
    }

    public void setRulerWidth(int mRulerWidth) {
        this.mRulerWidth = mRulerWidth;
        requestLayout();
    }

    public void setRulerColor(int mRulerColor) {
        this.mRulerColor = mRulerColor;
        if (mRulerPaint != null) {
            mRulerPaint.setColor(mRulerColor);
            requestLayout();
        }
    }

    public void setShowTopOfThumb(boolean isShowTopOfThumb) {
        this.isShowTopOfThumb = isShowTopOfThumb;
        requestLayout();
    }
}