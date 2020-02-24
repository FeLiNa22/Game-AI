package Chess;

import Utils.Tuple;
import Game.Player;

public abstract class Piece implements PieceTemplate {
  protected int x;
  protected int y;
  protected Character mark;
  protected Board board;
  protected Player assignedPlayer;

  public Piece(int x, int y, Board board, Player player) {
    this.x = x;
    this.y = y;
    this.board = board;
    this.assignedPlayer = player;
  }

  public Player getAssignedPlayer() {
    return assignedPlayer;
  }

  public Character getMark() {
    return mark;
  }

  public void setMark(Character mark) {
    this.mark = mark;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void moveTo(Tuple<Integer, Integer> cords) {
    this.x = cords.getFirst();
    this.y = cords.getSecond();
  }

}
