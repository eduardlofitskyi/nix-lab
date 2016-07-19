package com.nixsolutions.lab1.task2;

/**
 * That class represent simple shape - triangle
 *
 * @author Eduard Lofitskyi
 */
public class Triangle extends Shape{

    private Point a;
    private Point b;
    private Point c;

    /**
     *
     * @param a represent one of the three point of triangle
     * @param b represent second point of triangle
     * @param c is the third point of triangle
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Print all points of triangle in readable format
     */
    @Override
    public void draw() {
        System.out.printf("A: [%4d, %4d]\nB: [%4d, %4d]\nC: [%4d, %4d]\n\n",
                a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY());
    }

    /**
     * Print information about scaled triangle
     * <i>Scaled means that given points was multiplied by two</i>
     */
    @Override
    public void scale() {
        System.out.printf("A: [%4d, %4d]\nB: [%4d, %4d]\nC: [%4d, %4d]\n\n",
                a.getX() * 2, a.getY() * 2, b.getX() * 2, b.getY() * 2, c.getX() * 2, c.getY() * 2);
    }


    /**
     * Print information about moved triangle
     * <i>Moved means that given points was moved on ten position left/below</i>
     */
    @Override
    public void move() {
        System.out.printf("A: [%4d, %4d]\nB: [%4d, %4d]\nC: [%4d, %4d]\n\n",
                a.getX() - 10, a.getY() - 10, b.getX() - 10, b.getY() - 10, c.getX() - 10, c.getY() - 10);
    }
}
