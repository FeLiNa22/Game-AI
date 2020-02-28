package Chess;

import Utils.Tuple;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import Game.Board;

public class Player extends Game.Player<Move> {
  private Set<Piece> collection;
  public Player(String name) {
    super(name);
    this.collection = new HashSet<>();
  }

  public void addToCollection(Piece piece){
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

  private Cords parseMove(String move) {
    int x = move.charAt(0) - 'a' + 1;
    int y = Character.getNumericValue(move.charAt(1));
    return new Cords(x, y);
  }
}
