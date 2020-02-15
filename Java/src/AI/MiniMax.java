package AI;

import FourInARow.FourInARowMove;
import Game.Board;
import Game.Player;
import Game.Status;

public class MiniMax<S> {
  private int maxDepth;
  private int beta;
  private int alpha;
  private Board<S> board;
  private Player<S> player;
  private S bestMove;

  public MiniMax(int maxDepth, int alpha, int beta) {
    this.maxDepth = maxDepth;
    this.alpha = alpha;
    this.beta = beta;
  }

  public S getOptimalMove(Board<S> board, Player<S> player) {
    this.board = board;
    this.player = player;
    Max(0, alpha, beta);
    return bestMove;
  }

  private int Max(int depth, int alpha, int beta) {
    int best = -1000;
    if (depth >= maxDepth || board.getStatus(player) != Status.PLAYING) {
      return board.getStatus(player).value();
    }
    for (S m : board.getPossibleMoves(player)) {
      board.makeMove(m, player);
      int res = Min(depth + 1, alpha, beta);
      board.undoMove(player);
      board.drawBoard();
      if (res >= best) {
        best = res;
        bestMove = m;
      }
      alpha = Math.max(best, alpha);
      if (beta <= alpha) {
        return alpha;
      }
    }
    return best;
  }

  private int Min(int depth, int alpha, int beta) {
    int best = 1000;
    if (depth >= maxDepth || board.getStatus(player) != Status.PLAYING) {
      return board.getStatus(player).value();
    }
    for (S m : board.getPossibleMoves(player.getOpponent())) {
      board.makeMove(m, player.getOpponent());
      int res = Max(depth + 1, alpha, beta);
      board.undoMove(player.getOpponent());
      if (res <= best) {
        best = res;
        bestMove = m;
      }
      beta = Math.min(best, beta);
      if (beta <= alpha) {
        return beta;
      }
    }
    return best;
  }
}
