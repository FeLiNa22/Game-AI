package Chess.Pieces;

import Chess.Board;
import Chess.Move;
import Chess.Piece;
import Chess.Player;
import Chess.Point;

public class King extends Piece {

  public King(Point point, Board board, Player player) {
    super(point, board, player);
    setMark('*');
  }

  @Override
  public boolean validMove(Move move) {
    int moveX = move.getTo().getX();
    int moveY = move.getTo().getY();
    boolean top = point.getX() == moveX && point.getY() == moveY + 1;
    boolean bottom = point.getX() == moveX && point.getY() == moveY - 1;
    boolean right = point.getY() == moveY && point.getX() == moveX + 1;
    boolean left = point.getY() == moveY && point.getX() == moveX - 1;
    return left || right || bottom || top;
  }
}
