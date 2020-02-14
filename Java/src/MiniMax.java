import Game.Board;
import Game.Move;
import Game.Player;
import Game.Status;
import java.util.Set;

public class MiniMax<S> {
  private final int maxDepth;
  private final int beta;
  private int alpha;
  private Board<S> board;
  private Player player;
  private S bestMove;

  public MiniMax(int maxDepth, int alpha, int beta) {
    this.maxDepth = maxDepth;
    this.alpha = alpha;
    this.beta = beta;
  }

  public S getOptimalMove(Board<S> board, Player player) {
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
    for(S m : board.getPossibleMoves(player)){
      board.makeMove(m, player);
      int res = Min(depth + 1, alpha, beta);
      board.undoMove(player);
      if (res >= best) {
        best = res;
        bestMove = m;
      }
      alpha = Math.max(best, alpha);
      if (beta <= alpha) {
        break;
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
        break;
      }
    }
    return best;
  }
}
