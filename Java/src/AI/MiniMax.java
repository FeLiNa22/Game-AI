package AI;

import Game.Board;
import Game.Player;
import Game.Status;
import Utils.Tuple;

public class MiniMax<S> {
  private final int MIN;
  private final int MAX;
  private int maxDepth;
  private Board<S> board;
  private Player<S> player;

  public MiniMax(int maxDepth, int alpha, int beta) {
    this.maxDepth = maxDepth;
    this.MIN = alpha;
    this.MAX = beta;
  }

  public S getOptimalMove(Board<S> board, Player<S> player) {
    this.board = board;
    this.player = player;
    return minimax(0, MIN, MAX, true).getSecond();
  }

  private Tuple<Integer,S> minimax(int depth, int alpha, int beta, boolean maximisePlayer) {
    // If Terminal
    if (!board.getStatus(player).equals(Status.PLAYING)) {
      switch (board.getStatus(player)) {
        case WIN:
          return new Tuple<>(MAX - depth,null);
        case LOSE:
          return new Tuple<>(MIN + depth,null);
        default:
          return new Tuple<>(0,null);
      }
      // if max depth is reached
    } else if (depth == maxDepth) {
      // runs custom eval function for the board
      return new Tuple<>(board.customEvaluateFunction(player),null);
    } else if (maximisePlayer) {
      int best = MIN;
      S bestMove = null;
      for (S m : board.getPossibleMoves(player)) {
        board.makeMove(m, player);
        int res = minimax(depth + 1, alpha, beta, false).getFirst();
        board.undoMove(player);
        if (res > best) {
          best = res;
          bestMove = m;
        }
        alpha = Math.max(best, alpha);
        if (beta <= alpha) {
          break;
        }
      }
      return new Tuple<>(best,bestMove);
    } else {
      int best = MAX;
      S bestMove = null;
      for (S m : board.getPossibleMoves(player.getOpponent())) {
        board.makeMove(m, player.getOpponent());
        int res = minimax(depth + 1, alpha, beta, true).getFirst();
        board.undoMove(player.getOpponent());
        if(res < best){
          best = res;
          bestMove = m;
        }
        beta = Math.min(best, beta);
        if (beta <= alpha) {
          break;
        }
      }
      return new Tuple<>(best,bestMove);
    }
  }
}
