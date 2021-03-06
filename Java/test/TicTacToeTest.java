import AI.TicTacToeAgent;
import Game.Game;
import TicTacToe.Board;
import TicTacToe.Move;
import TicTacToe.Player;

public class TicTacToeTest {

  public static void main(String[] args) {
    TicTacToeAgent p2 = new TicTacToeAgent("AI-1",10);
    //Player p2 = new Player("Raul");
    TicTacToeAgent p1 = new TicTacToeAgent("AI-2",100);
    Board board = new Board();
    Game<Move> game = new Game<Move>("Chess", p1, p2, board);
    game.run();
  }
}
