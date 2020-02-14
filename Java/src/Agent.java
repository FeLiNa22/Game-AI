import FourInARow.FourInARowMove;
import Game.Board;
import Game.Move;
import Game.Player;

public class Agent extends Player{
  private MiniMax<FourInARowMove> minimax;
  public Agent(String name) {
    super(name);
    minimax = new MiniMax<>(6,-1000000,100000);
  }

  @Override
  public Move getMove(Board b) {
    return minimax.getOptimalMove(b,this);
  }
}
