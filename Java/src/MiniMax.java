import Game.Board;
import Game.Player;
import Game.Status;
import java.util.Set;

public class MiniMax<S> {
  private final int maxDepth;
  private final int beta;
  private int alpha;
  private Board<S> board;
  private Player player;

  public MiniMax(int maxDepth, int alpha, int beta) {
    this.maxDepth = maxDepth;
    this.alpha = alpha;
    this.beta = beta;
  }

  public S getOptimalMove(Board<S> board, Player player) {
    this.board = board;
    this.player = player;
    return Max(0, alpha, beta).getFirst();
  }


  public Tuple<S, Integer> Max(int depth, int alpha, int beta) {
    Integer best = -1000;
    S bestMove = null;
    if (depth >= maxDepth || board.getStatus(player) != Status.PLAYING) {
      return new Tuple<>(null, board.getStatus(player).value());
    }
    Set<S> possibleMoves = board.getPossibleMoves(player);
    for (S m : possibleMoves) {
      board.makeMove(m, player);
      Tuple<S, Integer> t = Min(depth + 1, alpha, beta);
      board.undoMove(player);
      if (t.getSecond() >= best) {
        best = t.getSecond();
        bestMove = t.getFirst();
      }
      alpha = Math.max(best, alpha);
      if (beta <= alpha) {
        break;
      }
    }
    return new Tuple<>(bestMove, best);
  }

  public Tuple<S, Integer> Min(int depth, int alpha, int beta) {
    Integer best = 1000;
    S bestMove = null;
    if (depth >= maxDepth || board.getStatus(player) != Status.PLAYING) {
      return new Tuple<>(null, board.getStatus(player).value());
    }
    Set<S> possibleMoves = board.getPossibleMoves(player.getOpponent());
    for (S m : possibleMoves) {
      board.makeMove(m, player.getOpponent());
      Tuple<S, Integer> t = Max(depth + 1, alpha, beta);
      board.undoMove(player.getOpponent());
      if (t.getSecond() <= best) {
        best = t.getSecond();
        bestMove = t.getFirst();
      }
      beta = Math.min(best, beta);
      if (beta <= alpha) {
        break;
      }
    }
    return new Tuple<>(bestMove, best);
  }
}
