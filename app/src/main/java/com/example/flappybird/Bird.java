package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

/**
 * This class is used to create the user controlled object "Bird" and draw it onto the canvas.
 *
 * @class Bird
 */

public class Bird extends BaseObject {

    // Defines an array for the bitmap which stores information about the bird.
    private ArrayList<Bitmap> arrBms = new ArrayList<>();

    // Creates variables which affect the Bird object.
    private int count, vFlap, idCurrentBitmap;
    private float drop;

    /**
     * Defines variables for the bird object.
     */
    public Bird() {
        this.count = 0;
        this.vFlap = 5;
        this.idCurrentBitmap = 0;
        this.drop = 0;
    }

    /**
     * Draws the bird onto the game canvas using its x and y variables.
     * @param canvas the canvas on which the bird is drawn using the bitmap "arrBms".
     */

    public void draw(Canvas canvas) {
        drop();
        canvas.drawBitmap(this.getBm(), this.x, this.y, null);
    }

    /**
     * Increases the drop speed and the bird y variable (larger the value the further down the
     * screen the bird will appear).
     */

    private void drop() {
        this.drop += 0.6;
        this.y += this.drop;
    }

    /**
     * Sets the array "arrBms" to the same as the inputted array. This is looped for the length of
     * the inputted array.
     * @param arrBms the inputted array.
     */

    public void setArrBms(ArrayList<Bitmap> arrBms) {
        this.arrBms = arrBms;
        for (int i = 0; i < arrBms.size(); i++) {
            this.arrBms.set(i, Bitmap.createScaledBitmap(this.arrBms.get(i), this.width, this.height, true));
        }
    }

    /**
     * Returns the desired bitmap based on the id set in the variable "idCurrentBitmap". This
     * bitmap is used by the canvas to draw the bird onto.
     * @return "bitmap" the bitmap which the canvas uses.
     */

    @Override
    public Bitmap getBm() {
        count++;
        if (this.count == this.vFlap) {
            for (int i = 0; i < arrBms.size(); i++) {
                if (i == arrBms.size()-1) {
                    this.idCurrentBitmap = 0;
                    break;
                } else if (this.idCurrentBitmap == i) {
                    idCurrentBitmap = i+1;
                    break;
                }
            }
            count = 0;
        }
        if (this.drop < 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(-25);
            return Bitmap.createBitmap(arrBms.get(idCurrentBitmap), 0, 0, arrBms.get(idCurrentBitmap).getWidth(), arrBms.get(idCurrentBitmap).getHeight(), matrix, true);
        } else if (drop >= 0) {
            Matrix matrix = new Matrix();
            if (drop < 70) {
                matrix.postRotate(-25+(drop*2));
            } else {
                matrix.postRotate(45);
            }
            return Bitmap.createBitmap(arrBms.get(idCurrentBitmap), 0, 0, arrBms.get(idCurrentBitmap).getWidth(), arrBms.get(idCurrentBitmap).getHeight(), matrix, true);
        }
        return this.arrBms.get(idCurrentBitmap);
    }

    /**
     * Returns the drop value which changes the bird's y variable (y axis / height).
     * @return drop the drop value.
     */

    public float getDrop() {
        return drop;
    }

    /**
     * Sets the drop value of the bird to the inputted float value.
     * @param drop the inputted float value.
     */

    public void setDrop(float drop) {
        this.drop = drop;
    }
}