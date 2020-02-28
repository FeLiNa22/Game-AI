package Chess;

import Utils.Tuple;

public class Move {
  private Cords from;
  private Cords to;
  private Piece piece;

  Move(Cords from, Cords to, Board chess) {
    this.from = from;
    this.to = to;
    this.piece = chess.getPiece(to);
  }

  public Piece getPiece() {
    return piece;
  }

  public Cords getFrom() {
    return from;
  }

  public Cords getTo() {
    return to;
  }

  @Override
  public String toString() {
    return "from=" + from +
        ", to=" + to +
        (piece.getMark() == '.' ? ", taken piece=" + piece : "") ;
  }
}
