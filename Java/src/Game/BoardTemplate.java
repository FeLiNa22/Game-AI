package Game;

import java.util.Set;

public interface BoardTemplate<S> {

  Status getStatus(Player p);

  boolean hasTied(Player p);

  boolean hasWon(Player p);

  boolean hasLost(Player p);

  boolean isValidMove(S move, Player p);

  void makeMove(S move, Player p);

  void undoMove(Player p);

  void drawBoard();

  void initialiseBoard(Player p1, Player p2);

  Set<S> getPossibleMoves(Player p);

  int customEvaluateFunction(Player p);
}
