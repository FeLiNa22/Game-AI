package Chess;

public class Move {

  private Point from;
  private Point to;
  private Piece piece;
  private boolean firstMoveAvailable;

  Move(Point from, Point to, Board board) {
    this.from = from;
    firstMoveAvailable = board.getPiece(from).isFirstMove();
    this.to = to;
    this.piece = board.getPiece(to);
  }

  public boolean getFirstMoveAvailable() {
    return firstMoveAvailable;
  }

  public Piece getPiece() {
    return piece;
  }

  public Point getFrom() {
    return from;
  }

  public Point getTo() {
    return to;
  }

  @Override
  public String toString() {
    return "from :" + from +
        ", to : " + to +
        (piece.getMark() == '.' ? "" : ", taken piece=" + piece.getMark());
  }
}
