import AI.Agent;
import FourInARow.*;
import FourInARow.Board;
import FourInARow.Player;
import Game.*;

public class Main {

  public static void main(String[] args) {
    Agent<Move> p1 = new Agent<>("AI-1",10);
    //Agent<FourInARowMove> p2 = new Agent<>("AI-2",12);
    Player p2 = new Player("Raul");
    Board board = new Board(6,6);
    Game<Move> game = new Game<Move>("Four In A Row", p1, p2, board);
    game.run();
  }
}
