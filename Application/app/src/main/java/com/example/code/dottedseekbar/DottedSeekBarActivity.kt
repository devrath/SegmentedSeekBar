package com.example.code.dottedseekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.code.R
import android.view.View

class DottedSeekBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dotted_seekbar)
        setThings()
    }

    private fun setThings() {
        val videoProgress = findViewById<View>(R.id.videoProgress) as DottedSeekBar
        val dots = intArrayOf(20, 30, 60, 90, 100)
        videoProgress.setDots(dots)
        videoProgress.setDotsDrawable(R.drawable.dot)
        videoProgress.setPadding(0, 0, 0, 0)
    }
}