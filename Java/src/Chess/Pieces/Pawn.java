package Chess.Pieces;

import Chess.Board;
import Chess.Cords;
import Chess.Piece;
import Chess.Player;

public class Pawn extends Piece {

  public Pawn(Cords cords, Board board, Player player) {
    super(cords, board, player);
    setMark('P');
  }

  @Override
  public boolean validMove(Cords cords) {
    int moveX = cords.getX();
    int moveY = cords.getY();
    boolean top = x == moveX;
    boolean right =
        x + 1 == moveX && board.getPiece(cords).getAssignedPlayer() == assignedPlayer.getOpponent();
    boolean left =
        x - 1 == moveX && board.getPiece(cords).getAssignedPlayer() == assignedPlayer.getOpponent();
    return (y == moveY + 1) && (left || right || top);
  }
}
