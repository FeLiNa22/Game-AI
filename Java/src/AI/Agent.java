package AI;

import Game.Board;
import Game.Player;

public class Agent<S> extends Player<S> {
  private MiniMax<S> minimax;
  public Agent(String name) {
    super(name);
    minimax = new MiniMax<>(10,-100000,100000);
  }

  @Override
  public S getMove(Board<S> b){
    return minimax.getOptimalMove(b,this);
  }
}
