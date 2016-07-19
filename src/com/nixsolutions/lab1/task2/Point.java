package com.nixsolutions.lab1.task2;

/**
 * Point class defines a point representing a location in (x,y) coordinate space.
 *
 * @author Eduard Lofitskyi
 */
public class Point {

    private final int DEFAULT_VALUE = 10;

    private int x;
    private int y;

    /**
     * create new point with default X and Y
     */
    public Point(){
        this.x = DEFAULT_VALUE;
        this.y = DEFAULT_VALUE;
    }

    /**
     * Create new Point with given params
     * @param x represent coordinate X in newly created Point instance
     * @param y represent coordinate Y in newly created Point instance
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
