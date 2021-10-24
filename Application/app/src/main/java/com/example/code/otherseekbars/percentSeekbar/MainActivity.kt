package com.example.code.otherseekbars.percentSeekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.code.R
import java.util.ArrayList

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar: PercentSeekBar = findViewById(R.id.percentSeekBar)
        progressBar.initData(prepareSections())


        Log.d("",prepareSections().toString())
    }

    private fun prepareSections(): ArrayList<ProgressItem> {

        val sectionOneVal = 20F
        val sectionTwoVal = 20F
        val sectionThreeVal = 40F
        val sectionFourVal = 10F

        val sectionDividerVal = 2F

        val numberOfSections = 4
        val numberOfDividers = (numberOfSections-1)// (TotalBlocks-1)

        val totalSpan = (sectionOneVal + sectionTwoVal + sectionThreeVal + sectionFourVal) + (numberOfDividers*sectionDividerVal)

        val segOneRange = getRangeInPercentage(input = sectionOneVal,max=totalSpan)
        val segTwoRange = getRangeInPercentage(input = sectionTwoVal,max=totalSpan)
        val segThreeRange = getRangeInPercentage(input = sectionThreeVal,max=totalSpan)
        val segFourRange = getRangeInPercentage(input = sectionFourVal,max=totalSpan)

        val segDividerRange = getRangeInPercentage(input = sectionDividerVal,max=totalSpan)

        return arrayListOf(
            ProgressItem(segOneRange,R.color.red,false),
            ProgressItem(segDividerRange,R.color.screen_background,true),
            ProgressItem(segTwoRange,R.color.blue,false),
            ProgressItem(segDividerRange,R.color.screen_background,true),
            ProgressItem(segThreeRange,R.color.green,false),
            ProgressItem(segDividerRange,R.color.screen_background,true),
            ProgressItem(segFourRange,R.color.yellow,false)
        )
    }

    private fun getRangeInPercentage(input:Float,max:Float): Float {
        return input/max*100
    }

}