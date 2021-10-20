package com.example.code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatSeekBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: ProgressBar = findViewById<View>(R.id.progress_bar_test) as AppCompatSeekBar
        val bgProgress = ProgressBarDrawable(prepareSections())
        progressBar.progressDrawable = bgProgress
    }

    private fun prepareSections(): List<Section> {
        return listOf(
            Section(1),Section(2),Section(3),
            Section(4)
        )
    }
}