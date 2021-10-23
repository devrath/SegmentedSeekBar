package com.example.code.customSeekBar.modified

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.code.R

class YoutubeSegmentedSeekBarActivity : AppCompatActivity() {

    private lateinit var youtubeSegmentedSeekBar: YoutubeSegmentedSeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_segmented_seek_bar)

        youtubeSegmentedSeekBar = findViewById(R.id.indicatorProgressBar)
        youtubeSegmentedSeekBar.indicatorPositions = listOf(0.13F, 0.34F, 0.57F, 0.85F, 0.92F)

    }

}