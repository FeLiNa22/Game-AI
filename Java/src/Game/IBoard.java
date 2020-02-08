package Game;

import java.util.Set;

public interface IBoard<S> {

  boolean hasTied(Player p);

  boolean hasWon(Player p);

  boolean hasLost(Player p);

  boolean isValidMove(S move, Player p);

  void makeMove(S move, Player p);

  void undoMove(Player p);

  void drawBoard();

  Set<Move> getPossibleMoves(Player p);
}
