package com.example.checkers.game;

import com.example.checkers.gameLogic.ECellType;

public class Game {
    private ECellType currentPlayer;
    private ECellType currentOpponent;
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

    //    protected ECellType[][] board = new ECellType[][] {
//            {ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
//            {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
//            {ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
//            {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
//            {ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
//            {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
//            {ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
//            {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
//    };
//    protected ECellType[][] board = new ECellType[][] {
//        {ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
//        {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
//        {ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
//        {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
//        {ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
//        {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
//        {ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.PieceBlack, ECellType.EmptyWhite, ECellType.EmptyBlack},
//        {ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.PieceWhite, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite, ECellType.EmptyBlack, ECellType.EmptyWhite},
//    };

    public ECellType[][] getBoard() {
        return board;
    }

    public boolean isCurrentPlayerPiece(int x, int y) {
        return false;
    }

    public ECellType getCurrenPlayer() {
        return currentPlayer;
    }
}
