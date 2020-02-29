package Chess.Pieces;

import Chess.Board;
import Chess.Move;
import Chess.Piece;
import Chess.Player;
import Chess.Point;

public class Castle extends Piece {

  public Castle(Point point, Board board, Player player) {
    super(point, board, player);
    setMark('C');
  }

  @Override
  public boolean validMove(Move move) {
    return checkHorizontal(move) || checkVertical(move);
  }
}
