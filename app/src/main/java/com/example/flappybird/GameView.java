package com.example.flappybird;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * This class extends the view. It is called by the activity "PlayActivity" and when the boolean
 * "start" is true, it calls and creates objects from the functions "Bird", "Pipe" and "BaseObject"
 * to make the game animations play. It also contains the logic for when the game ends.
 *
 * @class GameView
 */

public class GameView extends View {

    // Define objects and variables used to display the objects in every frame.
    private Bird bird;
    private Handler handler;
    private Runnable r;

    // Define array of pipes (used to make multiple pipes appear on the screen).
    private ArrayList<Pipe> arrPipes;

    // Define variables to use on the objects / canvas.
    private int totalPipes, distance;
    private int score, bestscore = 0;
    private boolean start;

    // Context variable is used for Shared Pref (key-value store to save and retrieve data).
    private Context context;

    /**
     * This method handles the game and redraws it every frame using the Runnable variable "r". It
     * also loads the best score using SharedPreferences.
     * @param context
     * @param attrs
     */

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        // Gets the best score if it is not null and sets it to the variable "bestscore".
        SharedPreferences sp = context.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
        if (sp != null) {
            bestscore = sp.getInt("bestscore", 0);
        }
        score = 0;
        start = false;
        initBird();
        initPipe();
        handler = new Handler();

        // Rewrites the game every frame by calling the "invalidate" method to invalidate the
        // previous frame.
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    /**
     * This method creates the initial pipes (both top and bottom) and uses the ".randomY()" method
     * to randomly generate the pipes (features).
     */

    private void initPipe() {
        totalPipes = 6;
        distance = 400*Constants.SCREEN_HEIGHT/1920;
        arrPipes = new ArrayList<>();
        for (int i = 0; i < totalPipes; i++) {
            if (i < totalPipes/2) {
                // Adds the new pipes to the array and uses the screen width/height to position them.
                this.arrPipes.add(new Pipe(Constants.SCREEN_WIDTH+i* ((Constants.SCREEN_WIDTH+200*Constants.SCREEN_WIDTH/1080)/(totalPipes/2)), 0, 200*Constants.SCREEN_WIDTH/1080, Constants.SCREEN_HEIGHT/2));
                this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pipe_top));
                this.arrPipes.get(this.arrPipes.size()-1).randomY();
            }else {
                this.arrPipes.add(new Pipe(this.arrPipes.get(i-totalPipes/2).getX(), this.arrPipes.get(i-totalPipes/2).getY()+this.arrPipes.get(i-totalPipes/2).getHeight() + this.distance, 200*Constants.SCREEN_WIDTH/1080, Constants.SCREEN_HEIGHT/2));
                this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pipe_bottom));
            }
        }
    }

    /**
     * This method creates the initial bird object and uses the array "arrBms" to interchangeably
     * display the up and down image to make the bird appear to be flapping it's wings.
     */

    private void initBird() {
        bird = new Bird();
        bird.setWidth(100*Constants.SCREEN_WIDTH/1080);
        bird.setHeight(100*Constants.SCREEN_HEIGHT/1920);
        bird.setX(100*Constants.SCREEN_WIDTH/1080);
        bird.setY(Constants.SCREEN_HEIGHT/2-bird.getHeight()/2);
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.yellowbird_up));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.yellowbird_down));
        bird.setArrBms(arrBms);
    }

    /**
     * This method draws the pipes and the bird onto the inputted canvas. It also checks if the bird
     * collides with the pipes, causing the game over screen to be made visible, and checks if the
     * score needs to be increased. Additionally it checks if the best score count needs to be
     * increased, storing it using the SharedPreferences methods and generates new pipes if one goes
     * off of the visible screen (this method combines lots of other methods across classes with "if"
     * statements to provide game logic for all possible outcomes).
     * @param canvas
     */

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (start) {
            bird.draw(canvas);
            for (int i = 0; i < totalPipes; i++) {
                if (bird.getRect().intersect(arrPipes.get(i).getRect()) || bird.getY()-bird.getHeight() < 0 || bird.getY() > Constants.SCREEN_HEIGHT) {
                    Pipe.speed = 0;
                    PlayActivity.txt_score_over.setText(PlayActivity.txt_score.getText());
                    PlayActivity.txt_best_score.setText("best: " + bestscore);
                    PlayActivity.txt_score.setVisibility(INVISIBLE);
                    PlayActivity.rl_game_over.setVisibility(VISIBLE);
                }
                if (this.bird.getX()+this.bird.getWidth() > arrPipes.get(i).getX()+arrPipes.get(i).getWidth()/2
                        && this.bird.getX()+this.bird.getWidth() <= arrPipes.get(i).getX()+arrPipes.get(i).getWidth()/2+Pipe.speed
                        && i < totalPipes/2) {
                    score++;
                    if (score > bestscore) {
                        bestscore = score;
                        SharedPreferences sp = context.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("bestscore", bestscore);
                        editor.apply();
                    }
                    PlayActivity.txt_score.setText(""+score);
                }
                if (this.arrPipes.get(i).getX() < -arrPipes.get(i).getWidth()) {
                    this.arrPipes.get(i).setX(Constants.SCREEN_WIDTH);
                    if (i < totalPipes/2) {
                        arrPipes.get(i).randomY();
                    }else {
                        arrPipes.get(i).setY(this.arrPipes.get(i-totalPipes/2).getY()
                                +this.arrPipes.get(i-totalPipes/2).getHeight() + this.distance);
                    }
                }
                this.arrPipes.get(i).draw(canvas);
            }
        } else {
            if (bird.getY() > Constants.SCREEN_HEIGHT/2) {
                bird.setDrop(-15*Constants.SCREEN_HEIGHT/1920);
            }
            bird.draw(canvas);
        }
        handler.postDelayed(r, 10);
    }

    /**
     * This method returns true if the screen is tapped, causing the bird objects drop to be changed.
     * @param event the event being checked, in this case it is the entire screen.
     * @return the boolean being returned depending on if the screen is pressed down on.
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            bird.setDrop(-15);
        }
        return true;
    }

    /**
     * This function was used for testing if the start boolean was working.
     * @return returns the start booleans value.
     */

    public boolean isStart() {
        return start;
    }

    /**
     * This method sets the start to the same value as the inputted boolean.
     * @param start the inputted boolean.
     */

    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * This function resets the game (it doesn't restart it) and sets the score back to 0. It also
     * initialises the next set of pipes and the original bird position.
     */

    public void reset() {
        PlayActivity.txt_score.setText("0");
        score = 0;
        initPipe();
        initBird();
    }
}
