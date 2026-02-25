package com.example.whackamole;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.gridlayout.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
   ImageView img;
   Intent i;
   Button butt;
   TextView txt,time;
   boolean isclicked = false;
   CountDownTimer timer;
   int score =0;
   int count = 0;
   Handler handler;
    GridLayout Mygrid;
    Random rnd;
    SharedPreferences share;
    private ArrayList<ImageView> holes;
    private Runnable molesRunnable;
    final int holesnum = 9;
    int currentmoleindex = -1;
    int moleindex = 0;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
        createboard();
        GameOver();
        timer.start();
        handler.post(molesRunnable);

    }

    private void GameOver() {
        timer.cancel();
        handler.removeCallbacks(molesRunnable);
        for (ImageView hole : holes){
            hole.setImageResource(R.drawable.hole);

        }
        currentmoleindex = -1;
    }

    private void Initialize() {
        holes = new ArrayList<>();
        butt = findViewById(R.id.restbutton);
        share = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        time = findViewById(R.id.textView2);
        editor =  share.edit();
        i = new Intent(MainActivity.this, start_page.class);
        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onFinish() {
                time.setText("gameover");
                editor.apply();
                startActivity(i);
            }

            @Override
            public void onTick(long millisUntilFinished) {

                time.setText( millisUntilFinished / 1000 + "s");
            }
        };
        rnd = new Random();
        Mygrid = findViewById(R.id.gridLay);
        txt = findViewById(R.id.textView);
        txt.setText("score is: "+ score);
        handler = new Handler();
        molesRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentmoleindex != -1) {
                    holes.get(currentmoleindex).setImageResource(R.drawable.hole);


                }
                moleindex = rnd.nextInt(holesnum);
                holes.get(moleindex).setImageResource(R.drawable.mole);
                currentmoleindex = moleindex;
                handler.postDelayed(this, 1000);

            }
        };


    }

    private void createboard() {
        int column = (int)Math.sqrt(holesnum);
        for(int i =0;i<holesnum; i++){
            int col = i % column;
            int row = i / column;
            ImageView hole = new ImageView(this);
            hole.setImageResource(R.drawable.hole);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                    GridLayout.spec(row),
                    GridLayout.spec(col)
            );
            params.width = 250;
            params.height = 250;
            params.setMargins(10,10,10,10);
            hole.setLayoutParams(params);
            hole.setTag(i);
            isclicked = false;

            hole.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isclicked = true;
                    int clickhole = (int) v.getTag();


                    if(clickhole == moleindex){
                        score++;
                        txt.setText("score is: " + score);
                        editor.putInt("highest_score", count++);
                    }

                    }



            });


            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score = 0;
                    txt.setText("score is: " + score);
                    timer.start();

                }
            });
            holes.add(hole);
            Mygrid.addView(hole);
        }

    }
}