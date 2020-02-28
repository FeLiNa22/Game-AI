package Chess.Pieces;

import Chess.Board;
import Chess.Cords;
import Chess.Piece;
import Chess.Player;

public class King extends Piece {

  public King(Cords cords, Board board, Player player) {
    super(cords, board, player);
    setMark('*');
  }

  @Override
  public boolean validMove(Cords cords) {
    int moveX = cords.getX();
    int moveY = cords.getY();
    boolean top = x == moveX && y == moveY - 1;
    boolean right = x == moveX && y == moveY + 1;
    boolean left = x == moveX + 1 && y == moveY;
    boolean bottom = x == moveX - 1 && y == moveY;
    return left || right || bottom || top;
  }
}
