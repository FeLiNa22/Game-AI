package Game;

public abstract class Player<S> implements PlayerTemplate<S>{
  private String name;
  private Player opponent;
  private Character mark;

  public Player (String name) {
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

  public Player<S> getOpponent() {
    return opponent;
  }

  public void setOpponent(Player<S> opponent) {
    this.opponent = opponent;
  }
}
