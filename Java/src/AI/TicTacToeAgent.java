package AI;

import TicTacToe.Move;
import TicTacToe.Player;
import Game.Board;

public class TicTacToeAgent extends Player {
  private MiniMax<Move> minimax;
  public TicTacToeAgent(String name,int depth) {
    super(name);
    minimax = new MiniMax<>(depth,-100000,100000);
  }

  @Override
  public Move getMove(Board<Move> b) {
    return minimax.getOptimalMove(b,this);
  }

}
