import AI.FourInARowAgent;
import FourInARow.*;
import FourInARow.Board;
import FourInARow.Player;
import Game.*;

public class FourInARowTest {

  public static void main(String[] args) {
    FourInARowAgent p1 = new FourInARowAgent("AI",12);
    Player p2 = new Player("Random Player");
    Board board = new Board(6);
    Game<Move> game = new Game<Move>("Chess", p1, p2, board);
    game.run();
  }
}
