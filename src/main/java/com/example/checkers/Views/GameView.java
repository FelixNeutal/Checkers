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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GameView extends Stage{
    private Circle[][] boardPieces = new Circle[8][8];
    private Rectangle[][] board = new Rectangle[8][8];
    private Game game;
    private Circle previousPiece;
    private List<Cell> previousPath;
    private Circle currentPiece;
    private List<Cell> currentPath;

    public void run() {
        game = new HotSeatGame();
        Group root = new Group();
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
                    circle = drawPiece(board[y][x], Color.RED, 23);
                    boardPieces[y][x] = circle;
                    Circle finalCircle1 = circle;
//                    circle.setOnMouseEntered(s -> {
//                        onMouseEnter(finalCircle1);
//                    });
                    circle.setOnMouseClicked(event -> onMouseClicked(finalCircle1));
                    Circle finalCircle = circle;
//                    circle.setOnMouseExited(s -> {
//                        onMouseExit(finalCircle);
//                    });
                    root.getChildren().add(circle);
                } else if (pieces[y][x] == ECellType.PieceWhite) {
                    circle = drawPiece(board[y][x], Color.BEIGE, 23);
                    boardPieces[y][x] = circle;
                    Circle finalCircle1 = circle;
//                    circle.setOnMouseEntered(s -> {
//                        onMouseEnter(finalCircle1);
//                    });
                    circle.setOnMouseClicked(event -> onMouseClicked(finalCircle1));
                    Circle finalCircle = circle;
//                    circle.setOnMouseExited(s -> {
//                        onMouseExit(finalCircle);
//                    });
                    root.getChildren().add(circle);
                }
            }
        }
    }

    private void onMouseClicked(Circle circle) {
        if (isCurrentPlayerPiece(circle)) {
            currentPath = game.getPossibleMoves(circleToCell(circle));
            if (!currentPath.isEmpty()) {
                if (previousPiece != null) {
                    onMouseExit(previousPiece);
                    clearPath(previousPath);
                }
                previousPath = currentPath;
                previousPiece = circle;
                onMouseEnter(circle);
                drawPath(currentPath);
            }
        } else if (isLegalMove(circle)){
            // Make a move
            makeAMove(circle);
        }
    }

    private void onMouseClicked(Rectangle rectangle) {
        System.out.println("Rectangle clicked");
//        if (isLegalMove(circle)){
//            // Make a move
//            makeAMove(circle);
//        }
    }

    private void onMouseEnter(Circle circle) {
        if (isCurrentPlayerPiece(circle)) {
            if (game.getCurrenPlayer() == ECellType.PieceBlack) {
                circle.setStyle("-fx-fill: pink");
            } else {
                circle.setStyle("-fx-fill: Bisque");
            }
        }
    }

    private void onMouseExit(Circle circle) {
        if (isCurrentPlayerPiece(circle)) {
            if (game.getCurrenPlayer() == ECellType.PieceBlack) {
                circle.setStyle("-fx-fill: red");
            } else {
                circle.setStyle("-fx-fill: beige");
            }
        }
    }



    private void drawPath(List<Cell> path) {
        for (Cell cell : path) {
            if (boardPieces[cell.getY()][cell.getX()] == null) {
                board[cell.getY()][cell.getX()].setFill(Color.GREENYELLOW);
            }
        }
    }

    private void clearPath(List<Cell> path) {
        for (Cell cell : path) {
            if (boardPieces[cell.getY()][cell.getX()] == null) {
                board[cell.getY()][cell.getX()].setFill(Color.BLACK);
            }
        }
    }

    private void makeAMove(Circle startCircle) {
        System.out.println(currentPath);
        Cell start = circleToCell(startCircle);
        boardPieces[start.getY()][start.getX()] = null;
        Cell end = new Cell(-1, -1);
        for (Cell c : currentPath) {
            boardPieces[c.getY()][c.getX()] = null;
            end = new Cell(c.getX(), c.getY());
        }
        boardPieces[end.getY()][start.getX()] = startCircle;
    }

    private void makePiecesSelectable(ECellType pieceType) {

    }

    private Circle drawPiece(Rectangle rectangle, Color color, int radius) {
        Circle circle = new Circle(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, radius, color);
        return circle;
    }

    private boolean isLegalMove(Circle circle) {
        return currentPath.get(currentPath.size() - 1) == circleToCell(circle);
    }

    private boolean isCurrentPlayerPiece(Circle c) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (boardPieces[y][x] == c) {
                    return game.isCurrentPlayerPiece(x, y);
                }
            }
        }
        return false;
    }

    private Cell circleToCell(Circle circle) {
        Cell cell = null;
        for (int y = 0; y < boardPieces.length; y++) {
            for (int x = 0; x < boardPieces[0].length; x++) {
                if (boardPieces[y][x] == circle) {
                    cell = new Cell(x, y);
                }
            }
        }
        return cell;
    }
}
