package com.example.checkers.consoleGui;

import com.example.checkers.gameLogic.ECellType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleGui {
    public void DrawBoard(ECellType[][] board) {
        System.out.println("  +---+---+---+---+---+---+---+---+");
        for (int y = 0; y < 8; y++) {
            System.out.print((y + 1) + " |");
            for (int x = 0; x < 8; x++) {
                System.out.print(pieceToString(board[y][x]));
            }
            System.out.println();
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }
        System.out.println("    a   b   c   d   e   f   g   h");
    }

    public String getPlayerInput() {
        String playerMove = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Player input (piece new location)> ");
        try {
            playerMove = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerMove;
    }

    private String pieceToString(ECellType cellType) {
        switch (cellType) {
            case PieceBlack -> {
                return " B |";
            }

            case PieceWhite -> {
                return " W |";
            }
        }
        return "   |";
    }
}
