package Chess;

import Game.Board;
import Utils.Tuple;
import java.util.Scanner;

public class Player extends Game.Player<Move> {

  public Player(String name) {
    super(name);
  }

  @Override
  public Move getMove(Board<Move> b) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter piece to move (a5 b6): ");
    String from = sc.next();
    String to = sc.next();
    return new Move(parseMove(from), parseMove(to), (Chess.Board) b);
  }

  private Tuple<Integer, Integer> parseMove(String move) {
    int x = move.charAt(0) - 'a' + 1;
    int y = Character.getNumericValue(move.charAt(1));
    return new Tuple<>(x, y);
  }
}
