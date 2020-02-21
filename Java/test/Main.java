import AI.Agent;
import FourInARow.*;
import Game.*;

public class Main {

  public static void main(String[] args) {
    Agent<FourInARowMove> p2 = new Agent<>("AI");
    FourInARowPlayer p1 = new FourInARowPlayer("Raul");
    FourInARowBoard board = new FourInARowBoard(6,6);
    Game<FourInARowMove> game = new Game<FourInARowMove>("Four In A Row", p1, p2, board);
    game.run();
  }
}
