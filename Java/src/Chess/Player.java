package Chess;

import Game.Board;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Player extends Game.Player<Move> {
  private int direction;
  private Set<Piece> collection;
  private String color;

  public Player(String name) {
    super(name);
    this.collection = new HashSet<>();
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public int getDirection() {
    return direction;
  }

  public void addToCollection(Piece piece) {
    collection.add(piece);
  }

  public Set<Piece> getCollection() {
    return collection;
  }

  @Override
  public Move getMove(Board<Move> b) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter piece to move (a5 b6): ");
    String from = sc.next();
    String to = sc.next();
    return new Move(parseMove(from), parseMove(to), (Chess.Board) b);
  }

  private Point parseMove(String move) {
    int x = move.charAt(0) - 'a';
    int y = Character.getNumericValue(move.charAt(1)) - 1;
    return new Point(x, y);
  }
}
