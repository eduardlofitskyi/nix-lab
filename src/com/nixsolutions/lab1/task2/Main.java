package com.nixsolutions.lab1.task2;

import java.util.Random;

public class Main {

    public static Random random = new Random();

    public static void main(String[] args) {

        int shapesArrayLength = random.nextInt(91) + 10;

        Shape[] shapes = new Shape[shapesArrayLength];

        for (int i = 0; i < shapesArrayLength; i++){
            if (random.nextInt(2) == 0){
                shapes[i] = new Circle(new Point(random.nextInt(100), random.nextInt(100)), random.nextInt(100));
            } else {
                shapes[i] = new Triangle(new Point(random.nextInt(100), random.nextInt(100)),
                        new Point(random.nextInt(100), random.nextInt(100)), new Point(random.nextInt(100), random.nextInt(100)));
            }
        }

        int i = 0;
        for (Shape shape: shapes){
            System.out.printf("%2d)\t", i++);
            shape.draw();
        }

        i = 0;
        for (Shape shape: shapes){
            String s;
            if (shape instanceof Triangle){
                s = "TRIANGLE";
            } else if (shape instanceof Circle){
                s = "CIRCLE";
            } else {
                s = "UNKNOWN";
            }
            System.out.printf("%2d = %s\n", i++, s);
        }



    }
}
