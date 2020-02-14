package Game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

public class Board<S> implements IBoard<S> {
  protected int height;
  protected int width;
  protected Deque<S> moves ;

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    this.moves = new ArrayDeque<>();;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  @Override
  public Status getStatus(Player p) {
    if(hasWon(p)){
      return Status.WIN;
    }else if(hasLost(p)){
      return Status.LOSE;
    }else if(hasTied(p)){
      return Status.TIE;
    }else{
      return Status.PLAYING;
    }
  }

  @Override
  public Set<S> getPossibleMoves(Player p) {
    return null;
  }

  @Override
  public boolean hasTied(Player p) {
    return false;
  }

  @Override
  public boolean hasWon(Player p) {
    return false;
  }

  @Override
  public boolean hasLost(Player p) {
    return false;
  }

  @Override
  public boolean isValidMove(S move, Player p) {
    return false;
  }

  @Override
  public void makeMove(S move, Player p) {}

  @Override
  public void undoMove(Player p) { }


  @Override
  public void drawBoard() { }


}
