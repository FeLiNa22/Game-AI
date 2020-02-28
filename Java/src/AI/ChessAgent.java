package AI;

import Chess.Move;
import Chess.Player;
import Game.Board;

public class ChessAgent extends Player {
  private MiniMax<Move> minimax;
  public ChessAgent(String name,int depth) {
    super(name);
    minimax = new MiniMax<>(depth,-100000,100000);
  }

  @Override
  public Move getMove(Board<Move> b) {
    return minimax.getOptimalMove(b,this);
  }

}
