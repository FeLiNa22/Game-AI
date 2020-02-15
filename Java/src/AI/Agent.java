package AI;

import FourInARow.FourInARowMove;
import Game.Board;
import Game.Player;

public class Agent extends Player<FourInARowMove> {
  private MiniMax<FourInARowMove> minimax;
  public Agent(String name) {
    super(name);
    minimax = new MiniMax<>(6,-1000000,100000);
  }

  @Override
  public FourInARowMove getMove(Board<FourInARowMove> b){
    FourInARowMove move = minimax.getOptimalMove(b,this);
  System.out.println(move.getCol());
    return move;
  }
}
