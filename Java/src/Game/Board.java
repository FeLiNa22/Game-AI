package Game;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Board<Move> implements BoardTemplate<Move> {
  protected int height;
  protected int width;
  protected Deque<Move> moves;
  protected Character[][] board;

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    this.moves = new ArrayDeque<>();
    this.board = new Character[height][width];
  }
  protected void clearBoard(){
    // Sets up the initial Board as blank
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        board[j][i] = '.';
      }
    }
  }


  @Override
  public Status getStatus(Player<Move> p) {
    if (hasWon(p)) {
      return Status.WIN;
    } else if (hasLost(p)) {
      return Status.LOSE;
    } else if (hasTied(p)) {
      return Status.TIE;
    } else {
      return Status.PLAYING;
    }
  }


}
