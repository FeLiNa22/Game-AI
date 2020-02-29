package Chess.Pieces;

import Chess.Board;
import Chess.Move;
import Chess.Point;
import Chess.Piece;
import Chess.Player;
import java.util.Optional;

public class Bishop extends Piece {

  public Bishop(Point point, Board board, Player player) {
    super(point, board, player);
    setMark('B');
  }

  @Override
  public boolean validMove(Move move) {
    return checkMainDiagonal(move) || checkSecondDiagonal(move);
  }
}
