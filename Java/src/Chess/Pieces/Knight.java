package Chess.Pieces;

import Chess.Board;
import Chess.Move;
import Chess.Point;
import Chess.Piece;
import Chess.Player;

public class Knight extends Piece {

  public Knight(Point point, Board board, Player player) {
    super(point, board, player);
    setMark('K');
  }

  @Override
  public boolean validMove(Move move) {
    int moveX = move.getTo().getX();
    int moveY = move.getTo().getY();
    boolean right = point.getX() + 2 == moveX && (point.getY() == moveY - 1 || point.getY() == moveY + 1);
    boolean left = point.getX() - 2 == moveX && (point.getY() == moveY - 1 || point.getY() == moveY + 1);
    boolean top = point.getY() + 2 == moveY && (point.getX() == moveX - 1 || point.getX() == moveX + 1);
    boolean bottom = point.getY() - 2 == moveY && (point.getX() == moveX - 1 || point.getX() == moveX + 1);
    return left || right || bottom || top;
  }
}
