package com.example.flappybird;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    private ImageView bird;
    private float x, y, vy;
    private boolean isTapped = false;
    private int currentFrame = 0;
    private final int[] birdFrames = {R.drawable.yellowbird_down, R.drawable.yellowbird_up};
    private final int frameInterval = 100; // 100ms per frame
    private Handler handler;
    private ImageView pipeTop, pipeBottom;
    private int pipeX = 1000;
    private final int pipeGap = 300;
    private int pipeSpeed = 8;
    private int pipeTopY = 0;
    private int pipeBottomY = 0;
    private final int pipeWidth = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ImageView backgroundImage = (ImageView) findViewById(R.id.background_image);
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if (randomNumber == 0) {
            backgroundImage.setImageResource(R.drawable.background_day);
        } else {
            backgroundImage.setImageResource(R.drawable.background_night);
        }

        bird = findViewById(R.id.yellowbird);
        pipeTop = findViewById(R.id.pipetop);
        pipeBottom = findViewById(R.id.pipebottom);
        x = bird.getX();
        y = bird.getY();
        vy = 0;
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                update();
                handler.postDelayed(this, 10);
            }
        }, 10);
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isTapped = true;
                }
                return true;
            }
        });
    }

    private void update() {
        updateBird();
        updatePipes();
    }

    private void updateBird() {
        // Update bird position and velocity
        vy += 0.5; // Add gravity
        y += vy;
        bird.setY(y);
        // Update bird animation
        currentFrame = (currentFrame + 1) % birdFrames.length;
        bird.setImageResource(birdFrames[currentFrame]);
        // Handle tap event
        if (isTapped) {
            isTapped = false;
            // Make the bird hop up
            vy = -10; // Change vertical velocity
        }
    }

    private void updatePipes() {
        // Update pipe position
        pipeX -= pipeSpeed;
        pipeTop.setX(pipeX);
        pipeBottom.setX(pipeX);
        // Check if the pipe is off screen and move it to the right
        if (pipeX + pipeWidth < 0) {
            pipeX = 1000;
            // Randomize pipe position
            Random rand = new Random();
            int range = (int) (findViewById(android.R.id.content).getHeight() - pipeGap - 200);
            pipeTopY = rand.nextInt(range) + 100;
            pipeBottomY = pipeTopY + pipeGap;
            pipeTop.setY(pipeTopY - pipeTop.getHeight());
            pipeBottom.setY(pipeBottomY);
        }
        // Check for collision with pipes
        if (pipeX <= x + bird.getWidth() && pipeX + pipeWidth >= x) {
            if (y <= pipeTopY || y + bird.getHeight() >= pipeBottomY) {
                // Game over
            }
        }
    }
}