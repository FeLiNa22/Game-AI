package FourInARow;

import Game.Board;
import Game.Move;
import Game.Player;
import java.util.Scanner;

public class FourInARowPlayer extends Player {

  public FourInARowPlayer(String name) {
    super(name);
  }

  @Override
  public Move getMove(Board b) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter column :");
    int col = sc.nextInt();
    FourInARowMove m = new FourInARowMove(col - 1, b);
    System.out.println(m.getCol());
    return m;
  }
}
