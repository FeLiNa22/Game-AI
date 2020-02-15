package Game;

public abstract class Player<S> {
  private String name;
  private Player opponent;
  private Character mark;

  public Player(String name) {
    this.name = name;
  }

  public Character getMark() {
    return mark;
  }

  public void setMark(Character mark) {
    this.mark = mark;
  }

  public String getName() {
    return name;
  }

  public S getMove(Board<S> b) {
    return null;
  }

  public Player<S> getOpponent() {
    return opponent;
  }

  public void setOpponent(Player opponent) {
    this.opponent = opponent;
  }
}
