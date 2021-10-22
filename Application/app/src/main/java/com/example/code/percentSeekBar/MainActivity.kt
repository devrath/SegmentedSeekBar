package com.example.code.percentSeekBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.code.R
import com.example.code.percentSeekBar.PercentSeekBar
import com.example.code.percentSeekBar.ProgressItem
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

        val totalSpan = sectionOneVal+sectionTwoVal+sectionThreeVal+sectionFourVal

        val segOneRange = getRangeInPercentage(input = sectionOneVal,max=totalSpan)
        val segTwoRange = getRangeInPercentage(input = sectionTwoVal,max=totalSpan)
        val segThreeRange = getRangeInPercentage(input = sectionThreeVal,max=totalSpan)
        val segFourRange = getRangeInPercentage(input = sectionFourVal,max=totalSpan)

        return arrayListOf(
            ProgressItem(segOneRange,R.color.red),
            ProgressItem(segTwoRange,R.color.blue),
            ProgressItem(segThreeRange,R.color.green),
            ProgressItem(segFourRange,R.color.yellow)
        )
    }

    private fun getRangeInPercentage(input:Float,max:Float): Float {
        return input/max*100
    }

}