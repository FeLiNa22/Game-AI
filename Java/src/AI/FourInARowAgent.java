package AI;

import FourInARow.Move;
import FourInARow.Player;
import Game.Board;

public class FourInARowAgent extends Player {
  private MiniMax<Move> minimax;
  public FourInARowAgent(String name,int depth) {
    super(name);
    minimax = new MiniMax<>(depth,-100000,100000);
  }

  @Override
  public Move getMove(Board<Move> b) {
    return minimax.getOptimalMove(b,this);
  }

}
