package com.example.checkers.Controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GameViewController {
    @FXML
    private Pane gameBoard;

    private Rectangle[][] board = new Rectangle[8][8];
    private Rectangle[][] board = new Rectangle[8][8];
    private Paint previousColor = Color.BLACK;

    @FXML
    public void initialize() {
        drawBoard();
    }

    private void drawBoard() {
        int width = 50, height = 50;
        // W B W B W B W B
        // B W B W B W B W
        // W B W B W B W B
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Rectangle rectangle = new Rectangle(x * width, y * height, width, height);
                rectangle.setOnMouseClicked(mouseEvent -> onMouseClicked(mouseEvent));
                if (y % 2 == 0) {
                    if (x % 2 == 0) {
                        rectangle.setFill(Color.DARKGRAY);
                    } else {
                        rectangle.setFill(Color.BLACK);
                    }
                } else {
                    if (x % 2 == 0) {
                        rectangle.setFill(Color.BLACK);
                    } else {
                        rectangle.setFill(Color.DARKGRAY);
                    }
                }
                //rectangle.setOnMouseEntered(mouseEvent -> onMouseEntered(mouseEvent));
                //rectangle.setOnMouseExited(mouseEvent -> onMouseExited(mouseEvent));
                gameBoard.getChildren().add(rectangle);
            }
        }
    }

    private void onMouseClicked(MouseEvent event) {
        Rectangle r = (Rectangle) event.getSource();
        r.setFill(Color.PINK);
    }

    private void onMouseEntered(MouseEvent event) {
        Rectangle r = (Rectangle) event.getSource();
        previousColor = r.getFill();
        r.setFill(Color.PINK);
    }

    private void onMouseExited(MouseEvent event) {
        Rectangle r = (Rectangle) event.getSource();
        r.setFill(previousColor);
    }
}
