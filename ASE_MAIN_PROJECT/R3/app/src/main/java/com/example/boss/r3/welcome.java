package com.example.boss.r3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class welcome extends Activity implements OnInitListener {
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tts = new TextToSpeech(welcome.this, this);
        Button wButton = (Button) findViewById(R.id.welcomebutton);
        wButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                tts.speak("you are entering to r3", TextToSpeech.QUEUE_FLUSH, null);
                Intent i = new Intent(getApplicationContext(), Sortguide.class);
                startActivity(i);
            }
        });
    }
    @Override
        protected void onPause() {
            // TODO Auto-generated method stub
            super.onPause();
            tts.speak("Welcome to Recycle world", TextToSpeech.QUEUE_FLUSH, null);
        }
        @Override
        public void onInit(int status) {
            // TODO Auto-generated method stub

        }



}
