package Chess.Pieces;

import Chess.Board;
import Chess.Move;
import Chess.Point;
import Chess.Piece;
import Chess.Player;

public class Pawn extends Piece {

  public Pawn(Point point, Board board, Player player) {
    super(point, board, player);
    setMark('P');
  }

  @Override
  public boolean validMove(Move move) {
      int moveX = move.getTo().getX();
      int moveY = move.getTo().getY();
      boolean top = point.getX() == moveX
          && move.getPiece().getMark() == '.';
      boolean right =
          point.getX() == moveX + 1
              && move.getPiece().getAssignedPlayer() == assignedPlayer.getOpponent();
      boolean left =
          point.getX()  == moveX - 1
              && move.getPiece().getAssignedPlayer() == assignedPlayer.getOpponent();
      return ((point.getY() == moveY - assignedPlayer.getDirection()) && (left || right || top))
          || (firstMove && top && (point.getY() == moveY - 2 * assignedPlayer.getDirection()));
  }
}
