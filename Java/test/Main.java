import FourInARow.*;
import Game.*;

public class Main {

  public static void main(String[] args) {
    Player p2 = new FourInARowPlayer("AI");
    Player p1 = new FourInARowPlayer("Raul");
    Board board = new FourInARowBoard(6,6);
    Game game = new Game("Four In A Row", p1, p2, board);
    game.run();
  }
}
