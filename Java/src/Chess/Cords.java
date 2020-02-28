package Chess;

import Utils.Tuple;

public class Cords {

  private Tuple<Integer, Integer> cords;

  public Cords(int x, int y) {
    cords = new Tuple<>(x, y);
  }

  public int getX() {
    return cords.getFirst();
  }

  public int getY() {
    return cords.getSecond();
  }

  @Override
  public String toString() {
    return "(" + ((char) (getX() + 'a')) + "," + (getY() + 1) + ")";
  }

  public boolean equals(Cords cords) {
    return (getX() == cords.getX() && getY() == cords.getY());
  }
}
