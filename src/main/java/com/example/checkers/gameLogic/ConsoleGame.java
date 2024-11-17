package com.example.checkers.gameLogic;

import com.example.checkers.consoleGui.ConsoleGui;
import com.example.checkers.game.Game;
import com.example.checkers.game.HotSeatGame;

import java.util.List;

public class ConsoleGame extends GameLogic {
    ConsoleGui gui = new ConsoleGui();
    Game game = new HotSeatGame();
    public void run() {
        while (true) {
            gui.DrawBoard(game.getBoard());
            String input = gui.getPlayerInput();
            Cell[] cells = decipherInput(input);
            Cell start = cells[0];
            Cell end = cells[1];
            List<Cell> singleMoves = getSingleMoves(start, getMovementDirection(), game.getBoard());
            List<List<Cell>> jumps = getAllPossibleJumps(start, getMovementDirection(), game.getBoard());
            for (List jumpList : jumps) {
                System.out.println("JumpList" + jumpList);
            }
            if (!isLegalStart(start, game.getBoard())) {
                System.out.println("Not your piece!");
                continue;
            }
            //TODO
            // If there are jumps available, disable single moves!!!
            if (canMove(singleMoves, end)) {
                if (!canJump(jumps, end)) {
                    makeSingleMove(start, end, singleMoves, game.getBoard());
                    switchPlayers();
                } else {
                    System.out.println("You must jump!");
                }
            } else if (canJump(jumps, end)) {
                makeJump(start, end, jumps, game.getBoard());
                switchPlayers();
            } else {
                System.out.println("Cant move");
            }
        }
    }

    private Cell[] decipherInput(String input) {
        input = input.toLowerCase();
        int pieceX, pieceY;
        int endPositionX, endPositionY;
        String[] splitString = input.split(" ");
        pieceY = splitString[0].toCharArray()[0] - 49;
        pieceX = splitString[0].toCharArray()[1] - 97;
        Cell start = new Cell(pieceX, pieceY);

        endPositionY = splitString[1].toCharArray()[0] - 49;
        endPositionX = splitString[1].toCharArray()[1] - 97;
        Cell end = new Cell(endPositionX, endPositionY);

        return new Cell[]{start, end};
    }
}