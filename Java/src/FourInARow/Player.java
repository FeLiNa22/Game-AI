package FourInARow;

import Game.Board;
import java.util.Scanner;

public class Player extends Game.Player<Move> {

  public Player(String name) {
    super(name);
  }

  @Override
  public Move getMove(Board<Move> b) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter column : ");
    int col = sc.nextInt();
    return new Move(col - 1);
  }
}
