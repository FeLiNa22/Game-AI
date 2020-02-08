package FourInARow;
import Game.*;

public class FourInARowMove extends Move {

  public FourInARowMove(int x, Board b) {
    super(x, b.getHeight()-1);
  }

  public int getCol(){
    return getX();
  }
}
