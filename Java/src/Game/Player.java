package Game;

public abstract class Player {
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

  public Move getMove(Board b) {
    return null;
  }

  public Player getOpponent() {
    return opponent;
  }

  public void setOpponent(Player opponent) {
    this.opponent = opponent;
  }
}
