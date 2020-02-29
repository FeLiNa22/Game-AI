package Chess.Pieces;

import Chess.Board;
import Chess.Move;
import Chess.Point;
import Chess.Piece;
import Chess.Player;
import java.util.Optional;

public class Queen extends Piece {

  public Queen(Point point, Board board, Player player) {
    super(point, board, player);
    setMark('^');
  }

  @Override
  public boolean validMove(Move move) {
    return checkHorizontal(move)||checkMainDiagonal(move)||checkSecondDiagonal(move)||checkVertical(move);
    }
}
