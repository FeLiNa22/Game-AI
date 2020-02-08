package FourInARow;

import java.util.HashSet;
import Game.*;
import java.util.Set;

public class FourInARowBoard extends Board<FourInARowMove> {

  private Character[][] board;

  public FourInARowBoard(int width, int height) {
    super(width, height);
    this.board = new Character[height][width];
    // Sets up the initial Game.Board
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        board[j][i] = '.';
      }
    }
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
    // Horizontal Win
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width - 3; i++) {
        if (board[j][i] == p.getMark()
            && board[j][i + 1] == p.getMark()
            && board[j][i + 2] == p.getMark()
            && board[j][i + 3] == p.getMark()) {
          return true;
        }
      }
    }
    // Vertical Win
    for (int j = 0; j < height - 3; j++) {
      for (int i = 0; i < width; i++) {
        if (board[j][i] == p.getMark()
            && board[j + 1][i] == p.getMark()
            && board[j + 2][i] == p.getMark()
            && board[j + 3][i] == p.getMark()) {
          return true;
        }
      }
    }
    // Diagonal top left -> bottom right
    for (int j = 0; j < height - 3; j++) {
      for (int i = 0; i < width - 3; i++) {
        if (board[j][i] == p.getMark()
            && board[j + 1][i + 1] == p.getMark()
            && board[j + 2][i + 2] == p.getMark()
            && board[j + 3][i + 3] == p.getMark()) {
          return true;
        }
      }
    }
    // Diagonal top right -> bottom left
    for (int j = 0; j < height - 3; j++) {
      for (int i = 3; i < width; i++) {
        if (board[j][i] == p.getMark()
            && board[j + 1][i - 1] == p.getMark()
            && board[j + 2][i - 2] == p.getMark()
            && board[j + 3][i - 3] == p.getMark()) {
          return true;
        }
      }
    }
  return false;
  }

  @Override
  public boolean hasLost(Player p) {
    return hasWon(p.getOpponent());
  }

  @Override
  public boolean isValidMove(FourInARowMove move, Player p) {
    if (move.getCol() >= 0 && move.getCol() < width) {
      // returns true if column not full yet
      return board[0][move.getCol()] == '.';
    } else {
      return false;
    }
  }

  @Override
  public void makeMove(FourInARowMove move, Player p) {
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
      boolean stop = false;
      FourInARowMove prevMove = moves.poll();
      // Sets the top counter in a column back to '.'
      for (int j = 0; j < height; j++) {
        if (board[j][prevMove.getCol()] != '.' && !stop) {
          board[j][prevMove.getCol()] = '.';
          stop = true;
        }
      }
    }
  }

  @Override
  public void drawBoard() {
    System.out.println("1 2 3 4 5 6");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("-------------------------");
  }

  @Override
  public Set<Move> getPossibleMoves(Player p) {
    Set<Move> possibleMoves = new HashSet<>();
    for (int i = 0; i < width; i++) {
      FourInARowMove m = new FourInARowMove(i, this);
      if (isValidMove(m, p)) {
        possibleMoves.add(m);
      }
    }
    return possibleMoves;
  }
}
