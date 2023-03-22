package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * This class is used for creating the setter and getter methods for various objects.
 *
 * @class BaseObject
 */

public class BaseObject {

    // Creates variables to store information on the object being passed to the methods.
    protected float x, y;
    protected int width, height;
    protected Rect rect;
    protected Bitmap bitmap;

    /**
     * Base constructor for defining objects.
     */

    public BaseObject() {
    }

    /**
     * Method that defines the objects variables to the same as the inputted ones:
     * @param x the position on x axis (width).
     * @param y
     * @param width
     * @param height
     */

    public BaseObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Bitmap getBm() {
        return bitmap;
    }

    public void setBm(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getRect() {
        return new Rect((int)this.x, (int)this.y, (int)this.x+this.width, (int)this.y+this.height);
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
