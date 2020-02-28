package Chess.Pieces;

import Chess.Board;
import Chess.Cords;
import Chess.Piece;
import Chess.Player;

public class Knight extends Piece {

  public Knight(Cords cords, Board board, Player player) {
    super(cords, board, player);
    setMark('K');
  }

  @Override
  public boolean validMove(Cords cords) {
    int moveX = cords.getX();
    int moveY = cords.getY();
    boolean right = x + 3 == moveX && (y == moveY - 1 || y == moveY + 1);
    boolean left = x - 3 == moveX && (y == moveY - 1 || y == moveY + 1);
    boolean top = y + 3 == moveY && (x == moveX - 1 || x == moveX + 1);
    boolean bottom = y - 3 == moveY && (x == moveX - 1 || x == moveX + 1);
    return left || right || bottom || top;
  }
}
