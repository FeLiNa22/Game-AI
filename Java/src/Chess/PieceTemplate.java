package Chess;

import Utils.Tuple;

public interface PieceTemplate {
  boolean validMove(Tuple<Integer, Integer> cords);
}
