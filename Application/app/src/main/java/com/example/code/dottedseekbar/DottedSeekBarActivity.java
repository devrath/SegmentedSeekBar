package com.example.code.dottedseekbar;

import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.code.R;

public class DottedSeekBarActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dotted_seekbar);

        setThings();
    }

    private void setThings() {
        DottedSeekBar videoProgress = (DottedSeekBar) findViewById(R.id.videoProgress);

        int[] dots = {20,30,60,90,100};
        videoProgress.setDots(dots);
        videoProgress.setDotsDrawable(R.drawable.dot);
        // Set custom thumb icon color here (Optional)
        videoProgress.getThumb().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_IN);
        // Add below line to avoid unnecessary SeekBar padding. (Optional)
        videoProgress.setPadding(0, 0, 0, 0);

    }
}
