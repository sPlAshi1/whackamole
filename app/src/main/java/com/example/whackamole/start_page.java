package com.example.whackamole;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class start_page extends AppCompatActivity {
    Button but;
    Intent i;
    TextView txt;
    int high = 0;
    SharedPreferences share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        txt = findViewById(R.id.high);
        i = new Intent(start_page.this , MainActivity.class);
          but = findViewById(R.id.button);
          but.setOnClickListener(v -> {
            startActivity(i);

          });
        share = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        int temp = share.getInt("highest_score", 0);
          if(high > temp)
            txt.setText("highest score is: " + high);
          else
              txt.setText("highest score is: " + String.valueOf(temp));

    }
}