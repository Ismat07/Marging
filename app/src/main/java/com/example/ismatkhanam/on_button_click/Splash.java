package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {

    MediaPlayer song;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.splash);
        song= MediaPlayer.create(Splash.this,R.raw.never_say_goodbye);

        //used to access Prefs class
        SharedPreferences getPrefs= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music=getPrefs.getBoolean("checkbox",true);
        if (music == true) {
            song.start();
        }

        //song.start();
        Thread timer=new Thread(){
            @Override
            public void run() {
                try{
                        sleep(5000);
                }catch (InterruptedException e){
                        e.printStackTrace();
                }
                finally {
                    Intent i=new Intent("com.example.ismatkhanam.on_button_click.MENU");
                    startActivity(i);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        song.release();
        finish();
    }
}
