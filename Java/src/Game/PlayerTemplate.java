package Game;

public interface PlayerTemplate<S> {
  Character getMark();

  void setMark(Character mark);

  String getName();

  S getMove(Board<S> b);

  Player<S> getOpponent();

  void setOpponent(Player<S> opponent);
}
