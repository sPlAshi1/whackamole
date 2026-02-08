package com.example.whackamole;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.gridlayout.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
   ImageView img;
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
        rnd = new Random();
        Mygrid = findViewById(R.id.gridLay);
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
            hole.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickhole = (int) v.getTag();
                }
            });
            holes.add(hole);
            Mygrid.addView(hole);
        }

    }
}