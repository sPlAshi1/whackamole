package com.example.whackamole;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.gridlayout.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   ImageView img;
    GridLayout Mygrid;
    private ArrayList<ImageView> holes = new ArrayList<>();
    final int holesnum = 9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createboard();
    }

    private void createboard() {
        int column = (int)Math.sqrt(holesnum);
        Mygrid = findViewById(R.id.gridLay);
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
            Mygrid.addView(hole);
        }

    }
}