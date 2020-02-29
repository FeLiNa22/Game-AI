package Chess;

import Utils.Tuple;

public class Point {

  private Tuple<Integer, Integer> cords;

  public Point(int x, int y) {
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
    return "(" + ((char) (getX() + 'a')) + (getY() + 1) + ")";
  }

  public boolean equals(Point point) {
    return (getX() == point.getX() && getY() == point.getY());
  }

  public Point add(Point point) {
    return new Point(getX() + point.getX(), getY() + point.getY());
  }



}
