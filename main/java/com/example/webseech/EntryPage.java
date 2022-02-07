package com.example.webseech;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EntryPage extends AppCompatActivity {

            TextView tv_url_txt;
            EditText linkpaste;
            Button btn_speak,btn_pause;
            TextToSpeech mtts;

            //int cmd = 1;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_entry_page);
                tv_url_txt = (TextView)findViewById(R.id.tv_url_txt_id);
                btn_speak = (Button) findViewById(R.id.btn_speak_id);
                btn_pause = (Button) findViewById(R.id.pause_id);
                linkpaste = (EditText) findViewById(R.id.linkpaste_id);
                tv_url_txt.setMovementMethod(new ScrollingMovementMethod());
                mtts = new TextToSpeech(EntryPage.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if( status == TextToSpeech.SUCCESS) {
                            mtts.setLanguage(Locale.US);
                        }

                    }
                });



                //   tv_url_txt.setScroller(context);
                btn_speak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getBodyText();
                        // try{Thread.sleep(30);}catch(InterruptedException e){System.out.println(e);}
                        //tv_url_txt.getText().toString().split("\\s+");


                        mtts.speak(tv_url_txt.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                    } });
                btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mtts.isSpeaking()) {
                    mtts.stop();
                    // mtts.shutdown();
                }
                else{
                    Toast.makeText(EntryPage.this, "Not speaking", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }


    @Override
    protected void onPause() {

        if (mtts != null || mtts.isSpeaking()) {
            mtts.stop();
            // mtts.shutdown();
        }
      super.onPause();
    }

    private void getBodyText() {
        String weburl = linkpaste.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    String url=weburl;//your website url
                    Document doc = (Document) Jsoup.connect(url).get();

                    Element body = doc.body();
                    builder.append(body.text());

                } catch (Exception e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_url_txt.setText(builder.toString().substring(0,2000));

                    }
                });
            }
        }).start();
    }
}