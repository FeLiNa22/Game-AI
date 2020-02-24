package Chess.Pieces;

import Chess.Board;
import Chess.Piece;
import Game.Player;
import Utils.Tuple;

public class Pawn extends Piece {
  public Pawn(int x, int y, Board board, Player player) {
    super(x, y, board, player);
    setMark('P');
  }

  @Override
  public boolean validMove(Tuple<Integer, Integer> cords) {
    int moveX = cords.getFirst();
    int moveY = cords.getSecond();
    boolean top = x == moveX;
    boolean right = x + 1 == moveX && board.getPiece(cords).getAssignedPlayer() == assignedPlayer;
    boolean left = x - 1 == moveX && board.getPiece(cords).getAssignedPlayer() == assignedPlayer;
    return (y == moveY + 1) && (left || right || top);
  }
}
