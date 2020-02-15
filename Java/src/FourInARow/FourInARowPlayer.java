package FourInARow;

import Game.Board;
import Game.Move;
import Game.Player;
import java.util.Scanner;

public class FourInARowPlayer extends Player<FourInARowMove> {

  public FourInARowPlayer(String name) {
    super(name);
  }

  @Override
  public FourInARowMove getMove(Board<FourInARowMove> b) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter column : ");
    int col = sc.nextInt();
    return new FourInARowMove(col - 1, b);
  }
}
