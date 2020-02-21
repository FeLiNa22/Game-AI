package FourInARow;
import Game.*;

public class FourInARowMove extends Move {

  public FourInARowMove(int x) {
    super(x, 5);
  }

  public int getCol(){
    return getX();
  }
}
