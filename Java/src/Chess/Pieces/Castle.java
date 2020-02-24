package Chess.Pieces;

import Chess.Board;
import Chess.Piece;
import Game.Player;
import Utils.Tuple;

public class Castle extends Piece {
  public Castle(int x, int y, Board board, Player player) {
    super(x, y, board, player);
    setMark('C');
  }

  @Override
  public boolean validMove(Tuple<Integer, Integer> cords) {
    int moveX = cords.getFirst();
    int moveY = cords.getSecond();
    return y == moveY || x == moveX;
  }
}
