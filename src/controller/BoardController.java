package controller;

import view.GameArea;
import model.Board;

public class BoardController {
    private Board model;
    private GameArea view;
    public void moveLeft() {
        model.moveLeft();
        view.repaint();
    }

    public void moveRight() {
        model.moveRight();
        view.repaint();
    }

    public void moveDown() {
        model.fallDown();
        view.repaint();
    }
    public BoardController(Board board){
        this.model = board;
        view = new GameArea(this, this.model);
    }

    public static void main(String[] args) {
        Board model  = new Board(10, 20);
        new BoardController(model);
    }

}
