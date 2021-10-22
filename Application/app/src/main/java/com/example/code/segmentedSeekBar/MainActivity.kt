package com.example.code.segmentedSeekBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatSeekBar
import com.example.code.R
import com.example.code.percentSeekBar.PercentSeekBar

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar: PercentSeekBar = findViewById(R.id.percentSeekBar)
        //seekBarFrame.setSectionDivider(prepareSections())


        Log.d("",prepareSections().toString())
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