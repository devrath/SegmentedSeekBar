package com.example.code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: SegmentSeekBar = findViewById<View>(R.id.seek_bar) as SegmentSeekBar
        progressBar.setMplSegmentStyle(
            segmentsList = prepareSections(),
            segmentDividerColor = ContextCompat.getColor(this, R.color.white) ,
            segmentDividerWidth = 15
        )
    }

    private fun prepareSections(): List<Segment> {
        return listOf(
            Segment(2),Segment(2),Segment(2),
            Segment(4)
        )
    }
}