package Chess.Pieces;

import Chess.Board;
import Chess.Piece;
import Game.Player;
import Utils.Tuple;

public class Queen extends Piece {
  public Queen(int x, int y, Board board, Player player) {
    super(x, y, board, player);
    setMark('^');
  }

  @Override
  public boolean validMove(Tuple<Integer, Integer> cords) {
    int moveX = cords.getFirst();
    int moveY = cords.getSecond();
    int diagonal = moveY - y / moveX - x;
    return diagonal == 1 || diagonal == -1 || y == moveY || x == moveX;
  }
}
