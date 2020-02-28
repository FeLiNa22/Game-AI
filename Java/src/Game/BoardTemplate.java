package Game;

import java.util.Set;

public interface BoardTemplate<Move> {

  Status getStatus(Player<Move> p);

  boolean hasTied(Player<Move>  p);

  boolean hasWon(Player<Move>  p);

  boolean hasLost(Player<Move>  p);

  boolean isValidMove(Move move, Player<Move>  p);

  void makeMove(Move move, Player<Move>  p);

  void undoMove(Player<Move>  p);

  void drawBoard();

  void initialiseBoard(Player<Move>  p1, Player<Move>  p2);

  Set<Move> getPossibleMoves(Player<Move>  p);

  int customEvaluateFunction(Player<Move>  p);
}
