package Chess.Pieces;

import Chess.Board;
import Chess.Piece;
import Chess.Player;
import Utils.Tuple;

public class Empty extends Piece {

  public Empty(int x, int y, Board board, Player player) {
    super(x, y, board, player);
    this.mark = ' ';
  }

  @Override
  public boolean validMove(Tuple<Integer, Integer> cords) {
    return true;
  }
}
