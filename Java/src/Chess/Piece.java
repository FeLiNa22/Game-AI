package Chess;

import Utils.Tuple;

public abstract class Piece implements PieceTemplate {
  protected int x;
  protected int y;
  protected Character mark;
  protected Board board;
  protected Player assignedPlayer;

  public Piece(Cords cords, Board board, Player player) {
    this.x = cords.getX();
    this.y = cords.getY();
    this.board = board;
    this.assignedPlayer = player;
    player.addToCollection(this);
    this.mark = '.';
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

  public Cords getCords() {
    return new Cords(x,y);
  }

  public void moveTo(Cords cords) {
    this.x = cords.getX();
    this.y = cords.getY();
  }

}
