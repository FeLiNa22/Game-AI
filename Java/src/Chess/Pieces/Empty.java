package Chess.Pieces;

import Chess.Board;
import Chess.Cords;
import Chess.Piece;
import Chess.Player;


public class Empty extends Piece {

  public Empty(Cords cords, Board board) {
    super(cords, board, new Player(""));
  }


  @Override
  public boolean validMove(Cords cords) {
    return false;
  }
}
