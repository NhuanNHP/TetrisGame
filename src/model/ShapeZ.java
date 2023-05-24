package model;

import java.awt.*;

public class ShapeZ extends AShape{
    public ShapeZ(int x, int y) {
        super(x, y);
        this.color = Color.ORANGE;
        this.block = new int[][] {
                {1,1,0},
                {0,1,1},
        };
    }
}
