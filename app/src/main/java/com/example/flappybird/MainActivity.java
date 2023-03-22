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

/**
 * This class represents the home / welcome screen. It can be used to access other screens in the
 * application, using buttons.
 *
 * @class MainActivity
 */

public class MainActivity extends AppCompatActivity {

    @Override

    /**
     * Called when the activity is first created. This is where the content's view is set and
     * where actions behind buttons can be set.
     *
     * @param savedInstanceState the bundle containing the activity's previously frozen state.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // Sets app to be full screen.
        setContentView(R.layout.activity_main);

        // Defines variables from widgets in the content view.
        ImageView backgroundImage = (ImageView) findViewById(R.id.background_image);
        Button playButton = findViewById(R.id.play_button);
        Button levelsButton = findViewById(R.id.levels_button);

        // Randomly sets the background image based on the generated integer.
        Random random = new Random();
        int randomNumber = random.nextInt(2);

        if (randomNumber == 0) {
            backgroundImage.setImageResource(R.drawable.background_day);
        }
        else {
            backgroundImage.setImageResource(R.drawable.background_night);
        }

        // Checks the background was loaded correctly using the Logcat.
        Log.d("MainActivity", "Random number: " + randomNumber);

        // Sets buttons OnClickListener so they lead to specific screens in the application.

        playButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method starts the activity screen "PlayActivity" when pressed.
             * @param v represents the widget that was clicked to start the onClick event.
             */

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });

        levelsButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method starts the activity screen "LevelsActivity" when pressed.
             * @param v represents the widget that was clicked to start the onClick event.
             */

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
                startActivity(intent);
            }
        });
    }
}