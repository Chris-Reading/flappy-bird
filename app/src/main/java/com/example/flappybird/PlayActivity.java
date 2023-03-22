package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This class represents the main game screen. It uses randomly generated features when creating
 * the level's structure.
 *
 * @class PlayActivity
 */

public class PlayActivity extends AppCompatActivity {

    // Create variables for widgets in the content view.

    public static TextView txt_score, txt_best_score,txt_score_over;
    public static RelativeLayout rl_game_over;
    public static Button btn_start, btn_home;
    private static GameView gv;

    /**
     * Called from the "MainActivity" activity. This is where the content's view is set and
     * where actions behind buttons can be set.
     *
     * @param savedInstanceState the bundle containing the activity's previously frozen state.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Defines variable which contains information about the windows dimensions etc.
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        // Defines the "Constants" class' variables to the screen dimensions in regards to pixels.
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(R.layout.activity_play);

        // Defines variables from widgets in the content view.
        txt_score = findViewById(R.id.txt_score);
        txt_best_score = findViewById(R.id.txt_best_score);
        txt_score_over = findViewById(R.id.txt_score_over);
        rl_game_over = findViewById(R.id.rl_game_over);
        btn_start = findViewById(R.id.btn_start);
        btn_home = findViewById(R.id.btn_home);
        gv = findViewById(R.id.gv);

        // Sets buttons OnClickListener so they lead to specific screens in the application.

        btn_start.setOnClickListener(new View.OnClickListener() {

            /**
             * This method "starts" the game and changes the visibility of widgets to their
             * necessary states.
             * @param v represents the widget that was clicked to start the onClick event.
             */

            @Override
            public void onClick(View v) {
                gv.setStart(true);
                txt_score.setVisibility(View.VISIBLE);
                btn_start.setVisibility(View.INVISIBLE);
                btn_home.setVisibility(View.INVISIBLE);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {

            /**
             * This method returns to the activity screen "MainActivity" when pressed.
             * @param v represents the widget that was clicked to start the onClick event.
             */

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rl_game_over.setOnClickListener(new View.OnClickListener() {

            /**
             * This method "resets" the game and changes the visibility of widgets to their
             * necessary states. It does not restart the game, only resets it.
             * @param v represents the widget that was clicked to start the onClick event.
             */

            @Override
            public void onClick(View v) {
                btn_start.setVisibility(View.VISIBLE);
                btn_home.setVisibility(View.VISIBLE);
                rl_game_over.setVisibility(View.INVISIBLE);
                gv.setStart(false);
                gv.reset();
            }
        });
    }
}