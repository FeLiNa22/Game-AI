package Chess.Pieces;

import Chess.Board;
import Chess.Piece;
import Game.Player;
import Utils.Tuple;

public class Knight extends Piece {
  public Knight(int x, int y, Board board, Player player) {
    super(x, y, board, player);
    setMark('K');
  }

  @Override
  public boolean validMove(Tuple<Integer, Integer> cords) {
    int moveX = cords.getFirst();
    int moveY = cords.getSecond();
    boolean right = x + 3 == moveX && (y == moveY - 1 || y == moveY + 1);
    boolean left = x - 3 == moveX && (y == moveY - 1 || y == moveY + 1);
    boolean top = y + 3 == moveY && (x == moveX - 1 || x == moveX + 1);
    boolean bottom = y - 3 == moveY && (x == moveX - 1 || x == moveX + 1);
    return left || right || bottom || top;
  }
}
