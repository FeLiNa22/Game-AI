package Chess.Pieces;

import Chess.Board;
import Chess.Piece;
import Game.Player;
import Utils.Tuple;

public class King extends Piece {
  public King(int x, int y, Board board, Player player) {
    super(x, y, board, player);
    setMark('^');
  }

  @Override
  public boolean validMove(Tuple<Integer, Integer> cords) {
    int moveX = cords.getFirst();
    int moveY = cords.getSecond();
    boolean top = x == moveX && y == moveY - 1;
    boolean right = x == moveX && y == moveY + 1;
    boolean left = x == moveX + 1 && y == moveY;
    boolean bottom = x == moveX - 1 && y == moveY;
    return left || right || bottom || top;
  }
}
