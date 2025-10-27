package com.example.checkers.Game;

public class Game {
    Enum[][] pieces;
    Enum[][] board;

    public Game() {
        initializeBoard();
        initializePieces();
    }

    public void initializeBoard() {
        board = new Enum[][]{
                {Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB},
                {Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB},
                {Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB},
                {Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB},
                {Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB},
                {Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB},
                {Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB},
                {Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB}
        };
    }

    public void initializePieces() {
        pieces = new Enum[][] {
                {Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB, Piece.WB, Piece.BB},
        };
    }
}
