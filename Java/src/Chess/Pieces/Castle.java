package Chess.Pieces;

import Chess.Board;
import Chess.Cords;
import Chess.Piece;
import Chess.Player;

public class Castle extends Piece {

  public Castle(Cords cords, Board board, Player player) {
    super(cords, board, player);
    setMark('C');
  }

  @Override
  public boolean validMove(Cords cords) {
    int moveX = cords.getX();
    int moveY = cords.getY();
    return y == moveY || x == moveX;
  }
}
