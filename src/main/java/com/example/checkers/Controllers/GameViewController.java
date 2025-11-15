package com.example.checkers.Controllers;

import com.example.checkers.Game.Coordinate;
import com.example.checkers.Game.Game;
import com.example.checkers.Game.Piece;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameViewController {
    @FXML
    private Pane gameBoard;

    private Rectangle[][] board = new Rectangle[8][8];
    private Circle[][] pieces = new Circle[8][8];
    private Paint previousColor = Color.BLACK;
    Game game = new Game();

    @FXML
    public void initialize() {
        drawBoard();
        drawPieces();
    }

    private void drawPieces() {
        Enum<Piece>[][] pieces = game.getBoard();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (pieces[y][x] == Piece.BP) {
                    // Draw black piece
                    Circle c = new Circle(board[y][x].getX() + 25, board[y][x].getY() + 25, 20, Color.RED);
                    c.setOnMouseClicked(mouseEvent -> onMouseClickedOnPiece(mouseEvent));
                    gameBoard.getChildren().add(c);
                    this.pieces[y][x] = c;
                    //drawPiece(board[y][x].getX() + 25, board[y][x].getY() + 25, 20, Color.RED);
                }
                if (pieces[y][x] == Piece.WP) {
                    // Draw white piece
                    Circle c = new Circle(board[y][x].getX() + 25, board[y][x].getY() + 25, 20, Color.BEIGE);
                    c.setOnMouseClicked(mouseEvent -> onMouseClickedOnPiece(mouseEvent));
                    gameBoard.getChildren().add(c);
                    this.pieces[y][x] = c;
                    //drawPiece(board[y][x].getX() + 25, board[y][x].getY() + 25, 20, Color.BEIGE);
                }
            }
        }
    }

    private void drawBoard() {
        int width = 50, height = 50;
        // W B W B W B W B
        // B W B W B W B W
        // W B W B W B W B
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Rectangle rectangle = new Rectangle(x * width, y * height, width, height);
                if (y % 2 == 0) {
                    if (x % 2 == 0) {
                        rectangle.setFill(Color.GRAY);
                    } else {
                        rectangle.setFill(Color.BLACK);
                        rectangle.setOnMouseClicked(mouseEvent -> onMouseClickedOnTile(mouseEvent));
                    }
                } else {
                    if (x % 2 == 0) {
                        rectangle.setFill(Color.BLACK);
                        rectangle.setOnMouseClicked(mouseEvent -> onMouseClickedOnTile(mouseEvent));
                    } else {
                        rectangle.setFill(Color.GRAY);
                    }
                }
                //rectangle.setOnMouseEntered(mouseEvent -> onMouseEntered(mouseEvent));
                //rectangle.setOnMouseExited(mouseEvent -> onMouseExited(mouseEvent));
                gameBoard.getChildren().add(rectangle);
                board[y][x] = rectangle;
            }
        }
    }

    private void drawPiece(double x, double y, double radius, Paint color) {
    }

    private void onMouseClickedOnTile(MouseEvent event) {
        Rectangle r = (Rectangle) event.getSource();
        Coordinate c = getBoardTileCoordinates(r);
        System.out.println(game.isCurrentPlayerPiece(c.getX(), c.getY()));
    }

    private void onMouseClickedOnPiece(MouseEvent event) {
        Circle r = (Circle) event.getSource();
        Coordinate c = getBoardPieceCoordinates(r);
        System.out.println(game.isCurrentPlayerPiece(c.getX(), c.getY()));
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

    private Coordinate getBoardTileCoordinates(Rectangle tile) {
        Coordinate c = new Coordinate(-1, -1);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (board[y][x] == tile) {
                    c = new Coordinate(x, y);
                }
            }
        }
        return c;
    }

    private Coordinate getBoardPieceCoordinates(Circle piece) {
        Coordinate c = new Coordinate(-1, -1);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (pieces[y][x] == piece) {
                    c = new Coordinate(x, y);
                }
            }
        }
        return c;
    }
}
