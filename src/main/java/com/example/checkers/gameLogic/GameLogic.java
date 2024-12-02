package com.example.checkers.gameLogic;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
//    public List<List<Cell>> getAllAvailableMoves(Cell start, int direction, ECellType[][] board) {
//        List<List<Cell>> jumps = getAllPossibleJumps(start, direction, board);
//        List<Cell> singleMoves = getSingleMoves(start, direction, board);
//        List<List<Cell>> single = new ArrayList<>();
//        if (jumps.size() >= 1) {
//            return jumps;
//        } else {
//            for (Cell s : singleMoves) {
//                List<Cell> move = new ArrayList<>();
//                move.add(s);
//                single.add(move);
//            }
//        }
//        return single;
//    }

    public List<Cell> getSingleMoves(Cell center, ECellType currentOpponent, ECellType[][] board) {
        int direction = getMovementDirection(currentOpponent);
        List<Cell> singleMoves = new ArrayList<>();
        int[][] positions = {{-1, -1 * direction}, {1, -1 * direction}};
        for (int[] pos : positions) {
            int newX = center.getX() + pos[0];
            int newY = center.getY() + pos[1];
            if (isLegalCoordinate(newX, newY)) { //upper left cell
                if (board[newY][newX] == ECellType.EmptyBlack) {
                    singleMoves.add(new Cell(newX, newY));
                }
            }
        }
        return singleMoves;
    }

    public List<List<Cell>> getAllPossibleJumps(Cell center, ECellType[][] board, ECellType currentOpponent) {
        List<List<Cell>> possibleMoves = new ArrayList<>();
        exploreMoves(center, new ArrayList<>(), new ArrayList<>(), possibleMoves, board, currentOpponent);
        return possibleMoves;
    }

    private void exploreMoves(Cell current, List<Cell> path, List<Cell> visited, List<List<Cell>> possibleJumps, ECellType[][] board, ECellType currentOpponent) {
        visited.add(current);
        boolean isFound = false;
        // try jumping top left
        isFound |= tryJumping(current, -1, -1, visited, path, possibleJumps, board, currentOpponent);
        // try jumping top right
        isFound |= tryJumping(current, 1, -1, visited, path, possibleJumps, board, currentOpponent);
        // try jumping bottom left
        isFound |= tryJumping(current, -1, 1, visited, path, possibleJumps, board, currentOpponent);
        // try jumping bottom right
        isFound |= tryJumping(current, 1, 1, visited, path, possibleJumps, board, currentOpponent);

        if (!isFound && path.size() > 1) {
            possibleJumps.add(new ArrayList<>(path));
        }

        visited.remove(current);
    }

    private boolean tryJumping(Cell current, int dx, int dy, List<Cell> visited, List<Cell> path, List<List<Cell>> possibleJumps, ECellType[][] board, ECellType currentOpponent) {
        // if end pos is empty black and mid pos is opponent piece
        // then jump is legal
        int endX = current.getX() + 2 * dx;
        int endY = current.getY() + 2 * dy;
        int midX = current.getX() + dx;
        int midY = current.getY() + dy;

        if (isLegalCoordinate(endX, endY) && isLegalCoordinate(midX, midY)) {
            if (board[endY][endX] == ECellType.EmptyBlack && board[midY][midX] == currentOpponent && !visited.contains(new Cell(endX, endY))) {
                List<Cell> newPath = new ArrayList<>(path);
                newPath.add(new Cell(midX, midY));
                newPath.add(new Cell(endX, endY));
                exploreMoves(new Cell(endX, endY), newPath, visited, possibleJumps, board, currentOpponent);
                return true;
            }
        }
        return false;
    }


    private boolean isLegalCoordinate(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    protected boolean isLegalStart(Cell start, ECellType[][] board, ECellType currentPlayer) {
        return board[start.getY()][start.getX()] == currentPlayer;
    }

    protected boolean canMove(List<Cell> moves, Cell endMove) {
        boolean canMove = false;
        if (moves.size() > 0) {
            for (Cell move : moves) {
                if (move.equals(endMove)) {
                    canMove = true;
                    break;
                }
            }
        }
        return canMove;
    }

    protected boolean canJump(List<List<Cell>> jumps, Cell jump) {
        boolean canMove = false;
        for (List jumpList : jumps) {
            if (jumpList.get(jumpList.size() - 1).equals(jump)) {
                canMove = true;
                break;
            }
        }
        return canMove;
    }

    protected void makeSingleMove(Cell start, Cell end, List<Cell> singleMoves, ECellType[][] board, ECellType currentPlayer) {
        board[end.getY()][end.getX()] = currentPlayer;
        board[start.getY()][start.getX()] = ECellType.EmptyBlack;
    }

    protected void makeJump(Cell start, Cell end, List<List<Cell>> jumpMoves, ECellType[][] board, ECellType currentPlayer, ECellType currentOpponent) {
        board[start.getY()][start.getX()] = ECellType.EmptyBlack;
        board[end.getY()][end.getX()] = currentPlayer;
        for (List<Cell> jumpList : jumpMoves) {
            if (jumpList.get(jumpList.size() - 1).equals(end)) {
                for (Cell jump : jumpList) {
                    if (board[jump.getY()][jump.getX()] == currentOpponent) {
                        // Take opponents piece
                        board[jump.getY()][jump.getX()] = ECellType.EmptyBlack;
                    }
                }
                break;
            }
        }
    }

    protected int getMovementDirection(ECellType currentOpponent) {
        return currentOpponent == ECellType.PieceWhite ? -1 : 1;
    }
    //Win
    // Capture All Opponent Pieces: A player wins if they capture all their opponent's pieces.
    // Block All Opponent Pieces: A player wins if they create a situation where the opponent has no legal moves.
    // Opponent Resigns: If an opponent concedes defeat, the other player wins.

    //Lose
    // All their pieces are captured.
    // They have no legal moves left, making it impossible to play their turn.
    // They resign.

    //Draw
    //Repeated Position: The same board position occurs three times, with the same player to move each time.
    //No Capture in 40 Moves: Neither player captures a piece or makes a move with a king within 40 consecutive moves (varies slightly by ruleset).
    //Mutual Agreement: Both players agree to a draw.
    //Impossibility of Win: It becomes clear that neither player can force a win, such as when only kings remain, and they endlessly evade each other.
}
