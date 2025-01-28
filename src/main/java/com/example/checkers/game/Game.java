package com.example.checkers.game;

import com.example.checkers.gameLogic.Cell;
import com.example.checkers.gameLogic.ECellType;
import com.example.checkers.gameLogic.GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

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

    public boolean isCurrentPlayerPiece(Cell cell) {
        return board[cell.getY()][cell.getX()] == currentPlayer;
    }

    public boolean isLegalMove(Cell cell, List<List<Cell>> currentPath) {
        for (List<Cell> c : currentPath) {
            if (c.get(c.size() - 1).equals(cell)) {
                return true;
            }
        }
        return false;
    }

    public ECellType getCurrenPlayer() {
        return currentPlayer;
    }

    public void switchCurrentPlayer() {
        ECellType tempPlayer = currentPlayer;
        currentPlayer = currentOpponent;
        currentOpponent = tempPlayer;
    }

    public List<List<Cell>> getPossibleMoves(Cell start) {
        List<List<Cell>> singleMoves = gameLogic.getSingleMoves(start, currentOpponent, board);
        List<List<Cell>> jumps = gameLogic.getAllPossibleJumps(start, board, currentOpponent);
        List<List<Cell>> allMoves = new ArrayList<>();
        if (!jumps.isEmpty()) {
            allMoves = jumps;
        } else
            allMoves = singleMoves;
        return allMoves;
    }

    public void makeAMove(List<Cell> path) {

    }
}
