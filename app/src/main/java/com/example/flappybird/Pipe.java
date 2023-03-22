package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * This class is used to create pipes and draw them onto the canvas.
 *
 * @class Pipe
 */

public class Pipe extends BaseObject {

    // Creates a variable to store the speed pipes move across the x axis (width of screen).
    public static int speed;

    /**
     * Sets the pipes position based on the following arguments it takes in (also defines pipe
     * speed based on the screen width defined in "Constants"):
     * @param x the position on the x axis (width).
     * @param y the position on the y axis (height).
     * @param width the width of the pipe.
     * @param height the height of the pipe.
     */

    public Pipe (float x, float y, int width, int height) {
        super(x, y, width, height);
        speed = 10*Constants.SCREEN_WIDTH/1080;
    }

    /**
     * Draws the pipes onto the game canvas using their x and y variables. The pipe's x variable
     * decreases (moves from right to left) each time the function is called based on the speed
     * variable.
     * @param canvas the canvas on which the pipes are drawn using a bitmap.
     */

    public void draw(Canvas canvas) {
        this.x -= speed;
        canvas.drawBitmap(this.bitmap, this.x, this.y, null);
    }

    /**
     * Generates a random height for the next pipe to be drawn at.
     */

    public void randomY () {
        Random r = new Random();
        this.y = r.nextInt((0+this.height/4)+1)-this.height/4;
    }

    /**
     * Defines the bitmap which is used by the draw method to add display the pipes on the canvas.
     * @param bitmap the bitmap used by the canvas.
     */

    @Override
    public void setBm(Bitmap bitmap) {
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
    }
}
