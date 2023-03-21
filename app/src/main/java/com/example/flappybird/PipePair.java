package com.example.flappybird;

import android.os.Handler;
import android.widget.ImageView;

import java.nio.channels.Pipe;

public class PipePair {
    private static final int pipeSpeed = 10;
    private static final int gap = 200;
    private static final int delayTime = 50;

    private int gapPos;
    private ImageView pipeTop, pipBottom;
    private android.os.Handler Handler;

    public PipePair(int width, int height) {

    }
}
