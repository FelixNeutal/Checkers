package com.example.checkers;

import com.example.checkers.gameLogic.ConsoleGame;
import com.example.checkers.gameLogic.GameLogic;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Circle[][] boardPieces = new Circle[8][8];
    GameLogic game;

    @Override
    public void start(Stage stage) throws IOException {
//        game = new HotSeatGame();
//        Rectangle[][] board;
//        Group root = new Group();
//        Scene scene = new Scene(root, 500, 500);
//        String css = this.getClass().getResource("Checkers.css").toExternalForm();
//        scene.getStylesheets().add(css);
//
//        board = createBoard(root);
//        //createPieces(root, board);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
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

//    private void createPieces(Group root, Rectangle[][] board) {
//        // Create pieces
//        ECellType[][] pieces = game.getBoard();
//        Circle circle;
//        for (int y = 0; y < 8; y++) {
//            for (int x = 0; x < 8; x++) {
//                if (pieces[y][x] == ECellType.PieceBlack) {
//                    circle = drawPiece(board[y][x], Color.RED, 23);
//                    boardPieces[y][x] = circle;
//                    Circle finalCircle1 = circle;
//                    circle.setOnMouseEntered(s -> {
//                        onMouseEnter(finalCircle1);
//                    });
//                    circle.setOnMouseClicked(event -> onMouseClicked(finalCircle1));
//                    Circle finalCircle = circle;
//                    circle.setOnMouseExited(s -> {
//                        onMouseExit(finalCircle);
//                    });
//                    root.getChildren().add(circle);
//                } else if (pieces[y][x] == ECellType.WhitePiece) {
//                    circle = drawPiece(board[y][x], Color.BEIGE, 23);
//                    boardPieces[y][x] = circle;
//                    Circle finalCircle1 = circle;
//                    circle.setOnMouseEntered(s -> {
//                        onMouseEnter(finalCircle1);
//                    });
//                    circle.setOnMouseClicked(event -> onMouseClicked(finalCircle1));
//                    Circle finalCircle = circle;
//                    circle.setOnMouseExited(s -> {
//                        onMouseExit(finalCircle);
//                    });
//                    root.getChildren().add(circle);
//                }
//            }
//        }
//    }
//
//    private void onMouseClicked(Circle circle) {
//        if (isCurrentPlayerPiece(circle)) {
//            //game.getPossibleMoves();
//        }
//    }
//
//    private void onMouseEnter(Circle circle) {
//        if (isCurrentPlayerPiece(circle)) {
//            if (game.getCurrenPlayer() == ECellType.BlackPiece) {
//                circle.setStyle("-fx-fill: pink");
//            } else {
//                circle.setStyle("-fx-fill: Bisque");
//            }
//        }
//    }
//
//    private void onMouseExit(Circle circle) {
//        if (isCurrentPlayerPiece(circle)) {
//            if (game.getCurrenPlayer() == ECellType.BlackPiece) {
//                circle.setStyle("-fx-fill: red");
//            } else {
//                circle.setStyle("-fx-fill: beige");
//            }
//        }
//    }
//
//    private void makePiecesSelectable(ECellType pieceType) {
//
//    }
//
//    private Circle drawPiece(Rectangle rectangle, Color color, int radius) {
//        Circle circle = new Circle(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, radius, color);
//        return circle;
//    }
//
//    private boolean isCurrentPlayerPiece(Circle c) {
//        for (int y = 0; y < 8; y++) {
//            for (int x = 0; x < 8; x++) {
//                if (boardPieces[y][x] == c) {
//                    return game.isCurrentPlayerPiece(x, y);
//                }
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) {
        //launch();
        ConsoleGame game = new ConsoleGame();
        game.run();
    }
}