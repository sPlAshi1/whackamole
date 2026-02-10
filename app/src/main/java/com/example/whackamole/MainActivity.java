package com.example.whackamole;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
   ImageView img;
   Button butt;
   TextView txt;
   boolean isclicked = false;
   int score =0;
   Handler handler;
    GridLayout Mygrid;
    Random rnd;
    private ArrayList<ImageView> holes;
    private Runnable molesRunnable;
    final int holesnum = 9;
    int currentmoleindex = -1;
    int moleindex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
        createboard();
        handler.post(molesRunnable);


    }

    private void Initialize() {
        holes = new ArrayList<>();
        butt = findViewById(R.id.restbutton);
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
                    }

                    }



            });


            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score = 0;
                    txt.setText("score is: " + score);
                }
            });
            holes.add(hole);
            Mygrid.addView(hole);
        }

    }
}