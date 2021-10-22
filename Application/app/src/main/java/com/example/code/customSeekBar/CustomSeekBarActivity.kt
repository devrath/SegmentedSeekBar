package com.example.code.customSeekBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.code.R
import com.example.code.percentSeekBar.ProgressItem


class CustomSeekBarActivity : AppCompatActivity() {

    private var seekbar: CustomSeekBar? = null
    private val totalSpan = 100f
    private val redSpan = 40f
    private val blueSpan = 20f
    private val greenSpan = 30f
    private val yellowSpan = 10f
    private val darkGreySpan = 0f

    private var progressItemList: ArrayList<ProgressItem>? = null
    private var mProgressItem: ProgressItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_seek_bar)
        seekbar = findViewById(R.id.customSeekBar)
        initDataToSeekbar();

    }

    private fun initDataToSeekbar() {
        progressItemList = ArrayList()
        // red span
        mProgressItem = ProgressItem()
        mProgressItem!!.progressItemPercentage = redSpan / totalSpan * 100
        Log.i("Mainactivity", mProgressItem!!.progressItemPercentage.toString() + "")
        mProgressItem!!.color = R.color.red
        progressItemList!!.add(mProgressItem!!)
        // blue span
        mProgressItem = ProgressItem()
        mProgressItem!!.progressItemPercentage = blueSpan / totalSpan * 100
        mProgressItem!!.color = R.color.blue
        progressItemList!!.add(mProgressItem!!)
        // green span
        mProgressItem = ProgressItem()
        mProgressItem!!.progressItemPercentage = greenSpan / totalSpan * 100
        mProgressItem!!.color = R.color.green
        progressItemList!!.add(mProgressItem!!)
        // yellow span
        mProgressItem = ProgressItem()
        mProgressItem!!.progressItemPercentage = yellowSpan / totalSpan * 100
        mProgressItem!!.color = R.color.yellow
        progressItemList!!.add(mProgressItem!!)
        // greyspan
        mProgressItem = ProgressItem()
        mProgressItem!!.progressItemPercentage = darkGreySpan / totalSpan * 100
        mProgressItem!!.color = R.color.grey
        progressItemList!!.add(mProgressItem!!)
        seekbar!!.initData(progressItemList)
        seekbar!!.invalidate()
    }


}