package model;

import java.awt.*;

public abstract class AShape {
    protected int x;
    protected int y;
    protected Color color;
    protected int[][] block;

    public AShape(int x, int y) {
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[][] getBlock() {
        return block;
    }

    public void setBlock(int[][] block) {
        this.block = block;
    }

    public int getHeight() {
        return this.block.length;
    }

    public int getWidth() {
        return this.block[0].length;
    }

    public void moveLeft() {
        this.x--;
    }

    public void moveRight() {
        this.x++;
    }

    public void moveDown() {
        this.y++;
    }
}
