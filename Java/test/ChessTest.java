import AI.ChessAgent;
import AI.FourInARowAgent;
import Chess.Board;
import Chess.Move;
import Chess.Player;
import Game.Game;

public class ChessTest {

  public static void main(String[] args) {
    ChessAgent p1 = new ChessAgent("AI-1",2);
    Player p2 = new Player("Raul");
    Board board = new Board();
    Game<Move> game = new Game<Move>("Chess", p1, p2, board);
    game.run();
  }
}
