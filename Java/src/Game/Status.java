package Game;

public enum Status {
  WIN,
  LOSE,
  TIE,
  PLAYING;

  public Integer value() {
    switch (this) {
      case WIN:
        return 1;
      case LOSE:
        return -1;
      default:
        return 0;
    }
  }
}