package Chess.Pieces;

import Chess.Board;
import Chess.Move;
import Chess.Point;
import Chess.Piece;
import Chess.Player;

public class Empty extends Piece {

  public Empty(Point point, Board board) {
    super(point, board, new Player(""));
  }


  @Override
  public boolean validMove(Move move) {
    return false;
  }
}
