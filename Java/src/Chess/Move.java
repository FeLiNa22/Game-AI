package Chess;

import Utils.Tuple;

public class Move {
  private Tuple<Integer, Integer> from;
  private Tuple<Integer, Integer> to;
  private Piece piece;

  Move(Tuple<Integer, Integer> from, Tuple<Integer, Integer> to, Board chess) {
    this.from = from;
    this.to = to;
    this.piece = chess.getPiece(to);
  }

  public Piece getPiece() {
    return piece;
  }

  public Tuple<Integer, Integer> getFrom() {
    return from;
  }

  public Tuple<Integer, Integer> getTo() {
    return to;
  }
}
