package TicTacToe;

import Game.Board;
import java.util.Scanner;

public class Player extends Game.Player<Move> {

  public Player(String name) {
    super(name);
  }

  @Override
  public Move getMove(Board<Move> b) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter piece to move (a5): ");
    return parseMove(sc.next());
  }

  private Move parseMove(String move) {
    int x = move.charAt(0) - 'a';
    int y = Character.getNumericValue(move.charAt(1)) - 1;
    return new Move(x, y);
  }
}
