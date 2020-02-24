package FourInARow;

import Game.Player;
import java.util.HashSet;
import java.util.Set;

public class Board extends Game.Board<Move> {

  public Board(int width, int height) {
    super(width, height);
  }

  @Override
  public void drawBoard() {
    for (int t = 0; t < width; t++) {
      System.out.print((t + 1) + " ");
    }
    System.out.println();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    for (int t = 0; t < width; t++) {
      System.out.print("- ");
    }
    System.out.println();
  }

  @Override
  public boolean hasTied(Player p) {
    // If any of the top of the columns are empty
    // Then there still can be some possible moves
    for (int i = 0; i < width; i++) {
      if (board[0][i] == '.') {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean hasWon(Player p) {
    return numOfNInRow(4, p) > 0;
  }

  @Override
  public boolean hasLost(Player p) {
    return hasWon(p.getOpponent());
  }

  @Override
  public boolean isValidMove(Move move, Player p) {
    if (move.getCol() >= 0 && move.getCol() < width) {
      // returns true if column not full yet
      return board[0][move.getCol()] == '.';
    } else {
      return false;
    }
  }

  @Override
  public void makeMove(Move move, Player p) {
    moves.push(move);
    boolean stop = false;
    for (int j = 0; j < height; j++) {
      if (j == height - 1 && !stop) {
        board[height - 1][move.getCol()] = p.getMark();
      } else if (board[j][move.getCol()] == '.' && board[j + 1][move.getCol()] != '.') {
        board[j][move.getCol()] = p.getMark();
        stop = true;
      }
    }
  }

  @Override
  public void undoMove(Player p) {
    if (!moves.isEmpty()) {
      Move prevMove = moves.poll();
      // Sets the top counter in a column back to '.'
      for (int j = 0; j < height; j++) {
        if (board[j][prevMove.getCol()] == p.getMark()) {
          board[j][prevMove.getCol()] = '.';
          break;
        }
      }
    }
  }

  @Override
  public void initialiseBoard(Player p1, Player p2) {
    clearBoard();
  }

  @Override
  public Set<Move> getPossibleMoves(Player p) {
    Set<Move> possibleMoves = new HashSet<>();
    for (int i = 0; i < width; i++) {
      Move m = new Move(i);
      if (isValidMove(m, p)) {
        possibleMoves.add(m);
      }
    }
    return possibleMoves;
  }

  @Override
  public int customEvaluateFunction(Player p) {
    return numOfNInRow(3, p) - numOfNInRow(3, p.getOpponent());
  }

  private int numOfNInRow(int nInARow, Player p) {
    int counter = 0;
    // Horizontal Win
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width - (nInARow - 1); i++) {
        boolean rowFound = true;
        for (int n = 0; n < nInARow; n++) {
          if (board[j][i + n] != p.getMark()) {
            rowFound = false;
          }
        }
        if (rowFound) {
          counter += 1;
        }
      }
    }

    // Vertical Win
    for (int j = 0; j < height - (nInARow - 1); j++) {
      for (int i = 0; i < width; i++) {
        boolean rowFound = true;
        for (int n = 0; n < nInARow; n++) {
          if (board[j + n][i] != p.getMark()) {
            rowFound = false;
          }
        }
        if (rowFound) {
          counter += 1;
        }
      }
    }
    // Diagonal top left -> bottom right
    for (int j = 0; j < height - (nInARow - 1); j++) {
      for (int i = 0; i < width - (nInARow - 1); i++) {
        boolean rowFound = true;
        for (int n = 0; n < nInARow; n++) {
          if (board[j + n][i + n] != p.getMark()) {
            rowFound = false;
          }
        }
        if (rowFound) {
          counter += 1;
        }
      }
    }
    // Diagonal top right -> bottom left
    for (int j = 0; j < height - (nInARow - 1); j++) {
      for (int i = nInARow - 1; i < width; i++) {
        boolean rowFound = true;
        for (int n = 0; n < nInARow; n++) {
          if (board[j + n][i - n] != p.getMark()) {
            rowFound = false;
          }
        }
        if (rowFound) {
          counter += 1;
        }
      }
    }
    return counter;
  }
}
