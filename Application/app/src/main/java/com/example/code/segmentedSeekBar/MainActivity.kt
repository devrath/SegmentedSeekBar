package com.example.code.segmentedSeekBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatSeekBar
import com.example.code.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val seekBarFrame: SeekBarFrame = findViewById(R.id.seekbarFrame)
        val progressBar: AppCompatSeekBar = findViewById<View>(R.id.seek_bar) as AppCompatSeekBar
        seekBarFrame.setSectionDivider(prepareSections())


    }

    private fun prepareSections(): List<SegmentFrame> {

        val sectionOneVal = 20
        val sectionTwoVal = 20
        val sectionThreeVal = 40
        val sectionFourVal = 10

        val rangeMin = 0
        val totalSpan = sectionOneVal+sectionTwoVal+sectionThreeVal+sectionFourVal

        val segOneRange = getRangeInPercentage(input = sectionOneVal,min = rangeMin,max=totalSpan)
        val segTwoRange = getRangeInPercentage(input = sectionOneVal+sectionTwoVal,min = rangeMin,max=totalSpan)
        val segThreeRange = getRangeInPercentage(input = sectionOneVal+sectionTwoVal+sectionThreeVal,min = rangeMin,max=totalSpan)
        val segFourRange = getRangeInPercentage(input = sectionOneVal+sectionTwoVal+sectionThreeVal+sectionFourVal,min = rangeMin,max=totalSpan)

        return listOf(
            SegmentFrame(segOneRange),
            SegmentFrame(segTwoRange),
            SegmentFrame(segThreeRange),
            SegmentFrame(segFourRange)
        )
    }

    private fun getRangeInPercentage(input:Int,min:Int,max:Int): Int {
        return ((input - min) * 100) / (max - min)
    }

}