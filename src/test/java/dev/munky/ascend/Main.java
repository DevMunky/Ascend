package dev.munky.ascend;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        spiral();
    }

    public static void spiral(){
        int increment = 1;
        int x = 0;  // 500 is center x
        int y = 0;  // 500 is center y
        var d = "right";
        int n = 1;

        for (int i = 0; i < 10; i++) {
            // change the direction
            if (i == Math.pow(n, 2) - n) {
                d = "right";
            } else if (i == Math.pow(n, 2)) {
                d = "down";
            } else if (i == Math.pow(n, 2) + n) {
                d = "left";
            } else if (i == Math.pow(n, 2) + (n * 2 + 1)) {
                d = "up";
                n += 2;
            }
            System.out.println(x + "," + y);
            // get the current x and y.
            switch (d) {
                case "right" -> x += increment;
                case "left" -> x -= increment;
                case "down" -> y += increment;
                default -> y -= increment;
            }
        }
    }
}
