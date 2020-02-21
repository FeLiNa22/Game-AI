package AI;

import Game.Board;
import Game.Move;
import Game.Player;
import Game.Status;

public class MiniMax<S> {
  private final int MIN;
  private final int MAX;
  private int maxDepth;
  private Board<S> board;
  private Player player;
  private S bestMove;

  public MiniMax(int maxDepth, int alpha, int beta) {
    this.maxDepth = maxDepth;
    this.MIN = alpha;
    this.MAX = beta;
  }

  public S getOptimalMove(Board<S> board, Player player) {
    this.board = board;
    this.player = player;
    mm(0, MIN, MAX, true);
    return bestMove;
  }

  private int mm(int depth, int alpha, int beta, boolean maximisePlayer) {
    // If Terminal
    if (!board.getStatus(player).equals(Status.PLAYING)) {
      switch (board.getStatus(player)) {
        case WIN:
          return MAX - depth;
        case LOSE:
          return MIN + depth;
        default:
          return 0;
      }
      // if max depth is reached
    } else if (depth == maxDepth) {
      return board.evaluate(player);
    } else if (maximisePlayer) {
      int best = MIN;
      for (S m : board.getPossibleMoves(player)) {
        board.makeMove(m, player);
        int res = mm(depth + 1, alpha, beta, false);
        board.undoMove(player);
        if (res > best) {
          best = res;
          // Gets the best Move
          if (depth == 0) {
            bestMove = m;
          }
        }
        alpha = Math.max(best, alpha);
        if (beta <= alpha) {
          break;
        }
      }
      return best;
    } else {
      int best = MAX;
      for (S m : board.getPossibleMoves(player.getOpponent())) {
        board.makeMove(m, player.getOpponent());
        int res = mm(depth + 1, alpha, beta, true);
        board.undoMove(player.getOpponent());
        if (res < best) {
          best = res;
        }
        beta = Math.min(best, beta);
        if (beta <= alpha) {
          break;
        }
      }
      return best;
    }
  }
}
