package Chess.Pieces;

import Chess.Board;
import Chess.Cords;
import Chess.Piece;
import Chess.Player;

public class Queen extends Piece {
  public Queen(Cords cords, Board board, Player player) {
    super(cords, board, player);
    setMark('^');
  }

  @Override
  public boolean validMove(Cords cords) {
    int moveX = cords.getX();
    int moveY = cords.getY();
    int diagonal = (moveX - x != 0) ?  (moveY - y) / (moveX - x) : 0;
    return diagonal == 1 || diagonal == -1 || y == moveY || x == moveX;
  }
}
