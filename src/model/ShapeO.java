package model;

import java.awt.*;

public class ShapeO extends AShape{
    public ShapeO(int x, int y) {
        super(x, y);
        this.color = Color.ORANGE;
        this.block = new int[][] {
                {1,1},
                {1,1},
        };
    }
}
