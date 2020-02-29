package TicTacToe;

import Game.Player;
import java.util.HashSet;
import java.util.Set;

public class Board extends Game.Board<Move> {

  public Board() {
    super(3, 3);
  }

  @Override
  public boolean hasTied(Player<Move> p) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (board[j][i] == '.') {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean hasWon(Player<Move> p) {
    return numOfNInRow(3, p) > 0;
  }

  @Override
  public boolean hasLost(Player<Move> p) {
    return hasWon(p.getOpponent());
  }

  @Override
  public boolean isValidMove(Move move, Player<Move> p) {
    if (isInBounds(move)) {
      return board[move.getY()][move.getX()] == '.';
    }
    return false;
  }

  private boolean isInBounds(Move move) {
    return (move.getX() >= 0 && move.getX() < width && move.getY() >= 0 && move.getY() < height);
  }

  @Override
  public void makeMove(Move move, Player<Move> p) {
    moves.push(move);
    board[move.getY()][move.getX()] = p.getMark();
  }

  @Override
  public void undoMove(Player<Move> p) {
    Move move = moves.poll();
    assert move != null;
    board[move.getY()][move.getX()] = '.';
  }

  @Override
  public void drawBoard() {
    for (int j = 0; j < height; j++) {
      System.out.print( (j+1) + " | ");
      for (int i = 0; i < width; i++) {
        System.out.print( board[j][i] + " ");
      }
      System.out.println();
    }
    System.out.println("   |a|b|c|");
  }

  @Override
  public void initialiseBoard(Player<Move> p1, Player<Move> p2) {
    clearBoard();
  }

  @Override
  public Set<Move> getPossibleMoves(Player<Move> p) {
    Set<Move> possibleMoves = new HashSet<>();
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        Move move=  new Move(i,j);
        if(isValidMove(move,p)){
          possibleMoves.add(move);
        }
      }
    }
    return possibleMoves;
  }

  @Override
  public int customEvaluateFunction(Player<Move> p) {
    return numOfNInRow(2, p) - numOfNInRow(2, p.getOpponent());
  }

  private int numOfNInRow(int nInARow, Player<Move> p) {
    int counter = 0;
    // Horizontal Win
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width - (nInARow - 1); i++) {
        boolean rowFound = true;
        for (int n = 0; n < nInARow; n++) {
          if (board[j][i + n] != p.getMark()) {
            rowFound = false;
            break;
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
            break;
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
            break;
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
            break;
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
