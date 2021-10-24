package com.example.code.otherseekbars.customSeekBar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.code.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CustomSeekActivity  : AppCompatActivity() {
    private lateinit var indicatorProgressBar: IndicatorProgressBar
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_seek_bar)

        indicatorProgressBar = findViewById(R.id.indicatorProgressBar)
        indicatorProgressBar.indicatorPositions = listOf(0.13F, 0.34F, 0.57F, 0.85F, 0.92F)

        updateCurrentTime()

        indicatorProgressBar.setOnClickListener {
            if (indicatorProgressBar.progress >= 1F) {
                updateCurrentTime()
            }
        }
    }

    private fun updateCurrentTime() {
        scope.launch {
            while (indicatorProgressBar.progress <= 1F) {
                Log.d(TAG, "In while loop")
                delay(33)
                /*runOnUiThread {
                    indicatorProgressBar.progress += 0.003F
                    Log.d(TAG, "Progress is now: ${indicatorProgressBar.progress}")
                }*/
            }

        }
    }
}