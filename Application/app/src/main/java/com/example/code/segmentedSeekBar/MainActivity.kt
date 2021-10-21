package com.example.code.segmentedSeekBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import com.example.code.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: AppCompatSeekBar = findViewById<View>(R.id.seek_bar) as AppCompatSeekBar

    }

    private fun prepareSections(): List<Segment> {
        return listOf(
            Segment(20, 0.0F),
            Segment(30,0.0F),
            Segment(40,0.0F),
            Segment(10,0.0F)
        )
    }
}