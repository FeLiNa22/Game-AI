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
    FourInARowMove m = minimax.getOptimalMove(b,this);
    System.out.println(m.getCol());
    return new FourInARowMove(3 - 1, b);
  }
}
