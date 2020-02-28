package Game;

public abstract class Player<Move> implements PlayerTemplate<Move>{
  private String name;
  private Player<Move> opponent;
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

  public Player<Move> getOpponent() {
    return opponent;
  }

  public void setOpponent(Player<Move> opponent) {
    this.opponent = opponent;
  }
}
