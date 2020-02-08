package Game;

public class Player {
  private String name;
  private Player opponent;
  private Character mark;

  public Player(String name){
    this.name = name;
  }

  public void setMark(Character mark) {
    this.mark = mark;
  }

  public Character getMark() {
    return mark;
  }

  public String getName() {
    return name;
  }

  public Move getMove(Board b) {
    return null;
  }

  public void setOpponent(Player opponent){
    this.opponent = opponent;
  }

  public Player getOpponent() {
    return opponent;
  }
}
