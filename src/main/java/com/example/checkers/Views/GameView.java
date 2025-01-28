package com.example.checkers.Views;

import com.example.checkers.game.Game;
import com.example.checkers.game.HotSeatGame;
import com.example.checkers.gameLogic.Cell;
import com.example.checkers.gameLogic.ECellType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GameView extends Stage{
    Group root;
    private Circle[][] boardPieces = new Circle[8][8];
    private Rectangle[][] board = new Rectangle[8][8];
    private Game game;
    private Cell previousPiece;
    private List<List<Cell>> previousPath;
    private Cell currentPiece;
    private List<List<Cell>> currentPath;

    public void run() {
        game = new HotSeatGame();
        root = new Group();
        Scene scene = new Scene(root, 500, 500);
        //String css = this.getClass().getResource("Checkers.css").toExternalForm();
        //scene.getStylesheets().add(css);

        board = createBoard(root);
        createPieces(root, board);
        this.setTitle("Hello!");
        this.setScene(scene);
        this.show();
    }

    private Rectangle[][] createBoard(Group root) {
        int startX = 10;
        int startY = 10;
        int width = 50;
        int height = 50;
        Rectangle[][] board = new Rectangle[8][8];
        // Create board
        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++) {
                Rectangle rectangle = new Rectangle(startX + width * x, startY + height * y, width, height);
                board[y - 1][x - 1] = rectangle;
                rectangle.setStroke(Color.BLACK);
                rectangle.setOnMouseClicked(event -> onMouseClicked(rectangle));
                if (y % 2 != 0) {
                    if (x % 2 != 0) {
                        rectangle.setFill(Color.WHITE);
                    } else {
                        rectangle.setFill(Color.BLACK);
                    }
                } else {
                    if (x % 2 == 0) {
                        rectangle.setFill(Color.WHITE);
                    } else {
                        rectangle.setFill(Color.BLACK);
                    }
                }
                root.getChildren().add(rectangle);
            }
        }
        return board;
    }

    private void createPieces(Group root, Rectangle[][] board) {
        // Create pieces
        ECellType[][] pieces = game.getBoard();
        Circle circle;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (pieces[y][x] == ECellType.PieceBlack) {
                    circle = drawPiece((int) board[y][x].getX(), (int) board[y][x].getY(), (int) board[y][x].getWidth(), (int) board[y][x].getHeight(), Color.RED, 23);
                    boardPieces[y][x] = circle;
                    Circle finalCircle2 = circle;
                    circle.setOnMouseClicked(event -> onMouseClicked(finalCircle2));
                    root.getChildren().add(circle);
                } else if (pieces[y][x] == ECellType.PieceWhite) {
                    circle = drawPiece((int) board[y][x].getX(), (int) board[y][x].getY(), (int) board[y][x].getWidth(), (int) board[y][x].getHeight(), Color.BEIGE, 23);
                    boardPieces[y][x] = circle;
                    Circle finalCircle1 = circle;
                    circle.setOnMouseClicked(event -> onMouseClicked(finalCircle1));
                    root.getChildren().add(circle);
                }
            }
        }
    }

    private void onMouseClicked(Circle circle) {
        if (game.isCurrentPlayerPiece(shapeToCell(circle))) {
            currentPath = game.getPossibleMoves(shapeToCell(circle));
            if (!currentPath.isEmpty()) {
                if (previousPiece != null) {
                    onMouseExit(boardPieces[previousPiece.getY()][previousPiece.getX()]);
                    clearPath(previousPath);
                }
                previousPath = currentPath;
                previousPiece = shapeToCell(circle);
                currentPiece = previousPiece;
                onMouseEnter(circle);
                drawPath(currentPath);
            }
        }
    }

    private void onMouseClicked(Rectangle rectangle) {
        if (game.isLegalMove(shapeToCell(rectangle), currentPath)) {
            //makeAMove();
        }
    }

    private void onMouseEnter(Circle circle) {
        if (game.isCurrentPlayerPiece(shapeToCell(circle))) {
            if (game.getCurrenPlayer() == ECellType.PieceBlack) {
                circle.setStyle("-fx-fill: pink");
            } else {
                circle.setStyle("-fx-fill: Bisque");
            }
        }
    }

    private void onMouseExit(Circle circle) {
        if (game.isCurrentPlayerPiece(shapeToCell(circle))) {
            if (game.getCurrenPlayer() == ECellType.PieceBlack) {
                circle.setStyle("-fx-fill: red");
            } else {
                circle.setStyle("-fx-fill: beige");
            }
        }
    }

    private void makeAMove(List<Cell> path) {
        Cell end = new Cell(-1, -1);
        for (List<Cell> list : currentPath) {
            for (Cell cell : list) {
                deleteBoardPiece(cell);
            }
        }
//        boardPieces[endY][endX] = boardPieces[currentPiece.getY()][currentPiece.getX()];
//        boardPieces[currentPiece.getY()][currentPiece.getX()] = null;
//        root.getChildren().remove(boardPieces[currentPiece.getY()][currentPiece.getX()]);
//        moveBoardPiece();
    }

    private void deleteBoardPiece(Cell cell) {
        root.getChildren().remove(boardPieces[cell.getY()][cell.getX()]);
        boardPieces[cell.getY()][cell.getX()] = null;
    }

    private void moveBoardPiece(Cell oldLocation, Cell newLocation) {
        //Circle circle = new Circle(x + width / 2, y + height / 2, radius, color);
        double newX = board[newLocation.getY()][newLocation.getX()].getX() + board[newLocation.getY()][newLocation.getX()].getWidth() / 2;
        double newY = board[newLocation.getY()][newLocation.getX()].getY() + board[newLocation.getY()][newLocation.getX()].getHeight() / 2;
        boardPieces[oldLocation.getY()][oldLocation.getX()].setCenterY(newY);
        boardPieces[oldLocation.getY()][oldLocation.getX()].setCenterX(newX);
    }

    private void drawPath(List<List<Cell>> path) {
        for (List<Cell> list : path) {
            for (Cell cell : list) {
                if (boardPieces[cell.getY()][cell.getX()] == null) {
                    board[cell.getY()][cell.getX()].setFill(Color.GREENYELLOW);
                }
            }
        }
    }

    private void clearPath(List<List<Cell>> path) {
        for (List<Cell> list : path) {
            for (Cell cell : list) {
                if (boardPieces[cell.getY()][cell.getX()] == null) {
                    board[cell.getY()][cell.getX()].setFill(Color.BLACK);
                }
            }
        }
    }

    private Circle drawPiece(int x, int y, int width, int height, Color color, int radius) {
        Circle circle = new Circle(x + width / 2, y + height / 2, radius, color);
        return circle;
    }

    public Cell shapeToCell(Shape shape) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (shape instanceof Rectangle && board[y][x] == shape) {
                    return new Cell(x, y);
                } else if (shape instanceof Circle && boardPieces[y][x] == shape) {
                    return new Cell(x, y);
                }
            }
        }
        return new Cell(-1, -1);
    }
}
