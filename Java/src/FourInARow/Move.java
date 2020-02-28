package FourInARow;

public class Move {

  private int col;

  Move(int col) {
    this.col = col;
  }

  int getCol(){
    return col;
  }

  @Override
  public String toString() {
    return
        "in col : " + col;
  }
}
