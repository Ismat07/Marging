package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.SeekBar;

public class SeekBarVolume extends Activity implements SeekBar.OnSeekBarChangeListener {

    SeekBar sb;
    MediaPlayer mp;
    AudioManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbarvolume);
        sb = (SeekBar)findViewById(R.id.sb_volume);
        mp = MediaPlayer.create(this,R.raw.never_say_goodbye);
        mp.start();
        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int max_v = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int cur_v = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb .setMax(max_v);
        sb.setProgress(cur_v);
        sb.setOnSeekBarChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.release();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
