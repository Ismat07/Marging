package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import java.util.Locale;
import java.util.Random;

public class TextVoice extends Activity implements View.OnClickListener {

    static final String[] texts = { "What's up!!!!!", "You smell!!!", "Do you like to eat something!!!" };
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textvoice);
        Button b = (Button)findViewById(R.id.b_text_to_voice);
        b.setOnClickListener(this);
        tts = new TextToSpeech(TextVoice.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        Random r = new Random();
        String  random = texts[r.nextInt(3)];
        tts.speak(random,TextToSpeech.QUEUE_FLUSH,null);
    }
}
