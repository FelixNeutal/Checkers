package com.example.checkers.game;

import com.example.checkers.gameLogic.Cell;
import com.example.checkers.gameLogic.ECellType;
import com.example.checkers.gameLogic.GameLogic;

import java.util.List;

public class Game {
    private ECellType currentPlayer;
    private ECellType currentOpponent;
    private GameLogic gameLogic;
    protected ECellType[][] board = new ECellType[][] {
            {ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack},
            {ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite},
            {ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack},
            {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
            {ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
            {ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite},
            {ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite},
            {ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite}
    };

    public Game() {
        currentPlayer = ECellType.PieceWhite;
        currentOpponent = ECellType.PieceBlack;
        gameLogic = new GameLogic();
    }

    public ECellType[][] getBoard() {
        return board;
    }

    public boolean isCurrentPlayerPiece(int x, int y) {
        return board[y][x] == currentPlayer;
    }

    public ECellType getCurrenPlayer() {
        return currentPlayer;
    }

    public void switchCurrentPlayer() {
        ECellType tempPlayer = currentPlayer;
        currentPlayer = currentOpponent;
        currentOpponent = tempPlayer;
    }

    public List<Cell> getPossibleMoves(Cell start) {
        List<Cell> singleMoves = gameLogic.getSingleMoves(start, currentOpponent, board);
        return singleMoves;
    }

    public void makeAMove(List<Cell> path) {

    }
}
