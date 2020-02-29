import AI.FourInARowAgent;
import FourInARow.*;
import FourInARow.Board;
import FourInARow.Player;
import Game.*;

public class FourInARowTest {

  public static void main(String[] args) {
    FourInARowAgent p1 = new FourInARowAgent("AI-1",4);
    FourInARowAgent p2 = new FourInARowAgent("AI-2",4);
    Board board = new Board(40);
    Game<Move> game = new Game<Move>("Chess", p1, p2, board);
    game.run();
  }
}
