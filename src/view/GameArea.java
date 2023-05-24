package view;

import controller.BoardController;
import model.AShape;
import model.Board;
import model.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameArea extends JPanel implements Observer, KeyListener {
    private static final int BLOCK_SIZE = 30;
    private Board model;
    private int rows;
    private int cols;
    private Color [][] filledBlocks;
    private AShape currentShape;

    private BoardController controller;
    public GameArea(BoardController controller, Board model){
        this.model = model;
        this.model.register(this);
        this.controller = controller;
        this.rows = model.getHeight();
        this.cols = model.getWidth();
        this.currentShape = model.getCurrentShape();
        this.filledBlocks = model.getCells();
        JFrame game = new JFrame("Tetris Game");
        game.setSize(450, 650);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setResizable(false);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
        this.setBackground(Color.black);
        game.add(this);
        game.setFocusable(true);
        game.requestFocusInWindow();
        game.addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        for (int y = 0; y < rows ; y++) {
            for (int x = 0; x < cols ; x++) {
                g.drawRect( x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE );
            }
        }
        drawBlock(g);
        drawFilledBlocks(g);
    }
    private void drawBlock(Graphics g){
        g.setColor(currentShape.getColor());
        int[][] block = currentShape.getBlock();
        int height = currentShape.getHeight();
        int width = currentShape.getWidth();
        for (int row = 0; row < height; row++) {
            for (int col= 0; col <  width; col++) {
                int x = (currentShape.getX() + col) * BLOCK_SIZE;
                int y = (currentShape.getY() + row) * BLOCK_SIZE;
                if (block[row][col] == 1){
                    g.fill3DRect(x, y, BLOCK_SIZE, BLOCK_SIZE, true);
                }
            }
        }
    }
    private void drawFilledBlocks(Graphics g){
        Color color;
        for (int r = 0; r < rows ; r++) {
            for (int c = 0; c < cols; c++) {
                color = filledBlocks[c][r];
                if (color != null){
                    int x = c * BLOCK_SIZE;
                    int y = r * BLOCK_SIZE;
                    g.setColor(color);
                    g.fill3DRect(x, y, BLOCK_SIZE, BLOCK_SIZE, true);
                }
            }
        }
    }

    @Override
    public void update(Board model) {
        this.model = model;
        this.currentShape = model.getCurrentShape();
        this.filledBlocks = model.getCells();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
           controller.moveLeft();
            System.out.println("Pressed LEFT key");
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            controller.moveRight();
            System.out.println("Pressed RIGHT key");
        } else if (keyCode == KeyEvent.VK_DOWN) {
            controller.moveDown();
            System.out.println("Pressed DOWN key");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
