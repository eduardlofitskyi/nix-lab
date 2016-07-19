package com.nixsolutions.lab1.task2;

/**
 * That class represent simple shape - circle
 *
 * @author Eduard Lofitskyi
 */
public class Circle extends Shape{

    private Point center;
    private int radius;

    /**
     *
     * @param center represent center point of circle
     * @param radius length of line segment from its center to its perimeter
     */
    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Print information about circle in readable format
     */
    @Override
    public void draw() {
        System.out.printf("Center: [%4d, %4d]\nRadius: %d\n\n", center.getX(), center.getY(), radius);
    }


    /**
     * Print information about scaled circle in readable format
     * <i>Scaled means that its radius was increased by two</i>
     */
    @Override
    public void scale() {
        System.out.printf("Center: [%4d, %4d]\nRadius: %d\n\n", center.getX(), center.getY(), radius * 2);
    }

    /**
     * Print information about moved circle in readable format
     * <i>Moved means that its center was moved left and below on ten positions</i>
     */
    @Override
    public void move() {
        System.out.printf("Center: [%4d, %4d]\nRadius: %d\n\n", center.getX() - 10, center.getY() - 10, radius);
    }
}
