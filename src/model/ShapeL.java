package model;

import java.awt.*;

public class ShapeL extends AShape {

    public ShapeL(int x, int y) {
        super(x, y);
        this.color = Color.ORANGE;
        this.block = new int[][] {
                {1,0},
                {1,0},
                {1,1}
        };
    }
}
