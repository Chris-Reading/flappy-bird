package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ImageView backgroundImage = (ImageView) findViewById(R.id.background_image);
        Random random = new Random();
        int randomNumber = random.nextInt(2);

// Set the image source based on the random number
        if (randomNumber == 0) {
            backgroundImage.setImageResource(R.drawable.background_day);
        }
        else {
            backgroundImage.setImageResource(R.drawable.background_night);
        }
        Log.d("MainActivity", "Random number: " + randomNumber);

        Button playButton = findViewById(R.id.play_button);
        Button levelsButton = findViewById(R.id.levels_button);
        Button statisticsButton = findViewById(R.id.statistics_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });

        levelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
                startActivity(intent);
            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });
    }
}