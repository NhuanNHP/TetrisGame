package model;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Board implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int height;
    private int width;
    private AShape currentShape;
    private Color[][] cells;
    private boolean isEndGame;
    private IShapeFactory factory;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Color[this.width][this.height];
        factory = new ShapeFactory();
        initShape();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                fallDown();
                notifyChanged();
                if (isEndGame) cancel();
            }
        };
        timer.schedule(timerTask, new Date(), 500);


    }

    public void initShape() {
        createNewShape();
    }

    private void createNewShape() {
        Random rd = new Random();
        int id = rd.nextInt(3);
        this.currentShape = factory.createShape(id, width / 2, 0);
    }

    public void fallDown() {
        moveDown();
        if (checkBottom()) {
            if (checkEndGame()) {
                System.out.println("het game");
                isEndGame = true;
            } else {
                createNewShape();
            }
        }
    }
    private boolean checkEndGame() {
        if (currentShape.getY() <= 1 && checkBottom()) return true;
        return false;
    }

    private boolean checkBottom() {
        int offset = this.currentShape.getY() + this.currentShape.getHeight();
        int[][] block = this.currentShape.getBlock();
        int w = this.currentShape.getWidth();
        int h = this.currentShape.getHeight();
        if (offset == height) {
            fillShape(block, w, h);
            return true;
        }
        for (int col = 0; col < w; col++) {
            for (int row = h - 1; row > 0; row--) {
                if (block[row][col] != 0) {
                    int x = col + currentShape.getX();
                    int y = row + currentShape.getY() + 1;
                    if (cells[x][y] != null) {
                        fillShape(block, w, h);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void fillShape(int[][] block, int width, int height) {
        int x = currentShape.getX();
        int y = currentShape.getY();
        Color color = currentShape.getColor();
        for (int r = 0; r < height; r++) {
            for (int col = 0; col < width; col++) {
                if (block[r][col] == 1) cells[col + x][r + y] = color;
            }
        }
    }

    public void moveLeft() {
        int offsetX = this.currentShape.getX();
        if (offsetX > 0) this.currentShape.moveLeft();
    }

    public void moveRight() {
        int offsetX = this.currentShape.getX() + this.currentShape.getWidth();
        if (offsetX < width) this.currentShape.moveRight();
    }

    public void moveDown() {
        this.currentShape.moveDown();
    }

    @Override
    public void register(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void remove(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyChanged() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public AShape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(AShape currentShape) {
        this.currentShape = currentShape;
    }

    public Color[][] getCells() {
        return cells;
    }

    public void setCells(Color[][] cells) {
        this.cells = cells;
    }

    public boolean isEndGame() {
        return isEndGame;
    }

    public void setEndGame(boolean endGame) {
        isEndGame = endGame;
    }


}
