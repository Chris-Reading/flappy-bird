package com.example.flappybird;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    // ...

    private static final int pipeSpeed = 10;
    private static final int gap = 200;
    private static final int delayTime = 50;

    private ImageView pipeTop, pipeBottom;
    private Handler Handler;
    private Random Random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        pipeTop = findViewById(R.id.PipeTop);
        pipeBottom = findViewById(R.id.PipeBottom);
        Random = new Random();

        Handler = new Handler();
        Handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updatePipes();
                Handler.postDelayed(this, delayTime);
            }
        }, delayTime);
    }

    private void updatePipes() {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int pipeHeight = pipeTop.getDrawable().getIntrinsicHeight();
        int maxGapPosition = screenHeight - gap - pipeHeight;

        // generate a new random gap position if the pipes have moved off screen
        if (pipeTop.getX() + pipeTop.getWidth() < 0) {
            int gapPos = Random.nextInt(maxGapPosition);
            pipeTop.setY(gapPos - pipeHeight);
            pipeBottom.setY(gapPos + gap);
        }

        // move the pipes to the left
        float newX = pipeTop.getX() - pipeSpeed;
        pipeTop.setX(newX);
        pipeBottom.setX(newX);
    }
}
