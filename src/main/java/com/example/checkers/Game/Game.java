package com.example.checkers.Game;

import java.util.List;

public class Game {
    Enum[][] board;
    Enum<Piece> currentPlayer;

    public Game() {
        initializeBoard();
        currentPlayer = Piece.WP;
    }

    private void initializeBoard() {
        board = new Enum[][]{
                {Piece.WB, Piece.BP, Piece.WB, Piece.BP, Piece.WB, Piece.BP, Piece.WB, Piece.BP},
                {Piece.BP, Piece.WB, Piece.BP, Piece.WB, Piece.BP, Piece.WB, Piece.BP, Piece.WB},
                {Piece.WB, Piece.BP, Piece.WB, Piece.BP, Piece.WB, Piece.BP, Piece.WB, Piece.BP},
                {Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB},
                {Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB},
                {Piece.WP, Piece.WB, Piece.WP, Piece.WB, Piece.WP, Piece.WB, Piece.WP, Piece.WB},
                {Piece.WB, Piece.WP, Piece.WB, Piece.WP, Piece.WB, Piece.WP, Piece.WB, Piece.WP},
                {Piece.WP, Piece.WB, Piece.WP, Piece.WB, Piece.WP, Piece.WB, Piece.WP, Piece.WB}
        };
    }

    public boolean isCurrentPlayerPiece(int x, int y) {
        return board[y][x] == currentPlayer;
    }

    public Enum<Piece>[][] getBoard() {
        return board;
    }

    private void removePieces(List<Coordinate> piecesToRemove) {
        for (Coordinate p : piecesToRemove) {
            board[p.getY()][p.getX()] = Piece.BB;
        }
    }

    private void moveCurrentPlayerPiece(Coordinate oldLocation, Coordinate newLocation) {
        board[oldLocation.getY()][oldLocation.getX()] = Piece.BB;
        board[newLocation.getY()][newLocation.getX()] = currentPlayer;
    }
}
