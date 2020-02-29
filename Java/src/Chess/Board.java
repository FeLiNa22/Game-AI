package Chess;

import Chess.Pieces.Bishop;
import Chess.Pieces.Castle;
import Chess.Pieces.Empty;
import Chess.Pieces.King;
import Chess.Pieces.Knight;
import Chess.Pieces.Pawn;
import Chess.Pieces.Queen;
import Game.Player;
import java.util.HashSet;
import java.util.Set;

public class Board extends Game.Board<Move> {

  private Piece[][] PieceBoard;

  public Board() {
    super(8, 8);
    this.PieceBoard = new Piece[8][8];
  }

  @Override
  public void drawBoard() {
    for (int t = 0; t < width; t++) {
      System.out.print("---");
    }
    System.out.println();
    for (int i = 0; i < height; i++) {
      System.out.print((i + 1) + "  | ");
      for (int j = 0; j < width; j++) {
        System.out.print(PieceBoard[i][j].getMark() + " ");
      }
      System.out.println();
    }
    System.out.println("    ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
    System.out.println("     a b c d e f g h ");
    for (int t = 0; t < width; t++) {
      System.out.print("---");
    }
    System.out.println();
  }

  public Piece getPiece(Point point) {
    return PieceBoard[point.getY()][point.getX()];
  }

  @Override
  public boolean hasTied(Player<Move> p) {
    return false;
  }

  @Override
  public boolean hasWon(Player<Move> p) {
    Chess.Player chessPlayer = (Chess.Player) p;
    for (Piece piece : chessPlayer.getCollection()) {
      if(piece.isActive()){
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean hasLost(Player<Move> p) {
    return hasWon(p.getOpponent());
  }

  @Override
  public boolean isValidMove(Move move, Player<Move> player) {
    Point from = move.getFrom();
    Point to = move.getTo();
    Chess.Player p = (Chess.Player) player;
    if (isInBounds(from)
        && isInBounds(to)
        && !isBlocked(to, p)
        && !to.equals(from)
        && (p == getPiece(from).getAssignedPlayer())) {
      return getPiece(from).validMove(move);
    } else {
      return false;
    }
  }

  private boolean isBlocked(Point point, Chess.Player p) {
    if (isInBounds(point)) {
      return getPiece(point).getAssignedPlayer() == p;
    }
    return false;
  }

  private boolean isInBounds(Point point) {
    int x = point.getX();
    int y = point.getY();
    return (x >= 0 && x < width && y >= 0 && y < height);
  }

  @Override
  public void makeMove(Move move, Player<Move> p) {
    moves.push(move);
    Point to = move.getTo();
    Point from = move.getFrom();
    // Makes the firstMove false as it has been used
    getPiece(from).setFirstMove(false);
    // updates cords of piece being moved
    getPiece(from).updateCords(to);
    setPiece(to,getPiece(from));
    // replaces old place with empty square
    setPiece(from,new Empty(from,this));
    // kills off the piece taken
    move.getPiece().deactivate();

  }

  @Override
  public void undoMove(Player<Move> p) {
    Move move = moves.poll();
    assert move != null;
    Point to = move.getTo();
    Point from = move.getFrom();
    // reverts piece moved to last pos
    getPiece(to).updateCords(from);
    setPiece(from,getPiece(to));
    // brings old piece back to life
    setPiece(to,move.getPiece());
    move.getPiece().activate();
    // reverts firstMove if it was used in the last move
    getPiece(from).setFirstMove(move.getFirstMoveAvailable());
  }

  private void setPiece(Point point,Piece piece){
    PieceBoard[point.getY()][point.getX()] = piece;
  }

  @Override
  public void initialiseBoard(Player<Move> player1, Player<Move> player2) {
    Chess.Player p1 = (Chess.Player) player1;
    Chess.Player p2 = (Chess.Player) player2;
    // Sets Player Direction Down
    p2.setDirection(1);
    // Sets Player Direction Up
    p1.setDirection(-1);
    int x = 0;
    int y = 0;
    // Castles
    PieceBoard[y][x] = new Castle(new Point(x, y), this, p2);
    PieceBoard[y][width - 1 - x] = new Castle(new Point(width - 1 - x, y), this, p2);
    PieceBoard[height - 1 - y][x] = new Castle(new Point(x, height - 1 - y), this, p1);
    PieceBoard[height - 1 - y][width - 1 - x] =
        new Castle(new Point(width - 1 - x, height - 1 - y), this, p1);
    x += 1;
    // Knights
    PieceBoard[y][x] = new Knight(new Point(x, y), this, p2);
    PieceBoard[y][width - 1 - x] = new Knight(new Point(width - 1 - x, y), this, p2);
    PieceBoard[height - 1 - y][x] = new Knight(new Point(x, height - 1 - y), this, p1);
    PieceBoard[height - 1 - y][width - 1 - x] =
        new Knight(new Point(width - 1 - x, height - 1 - y), this, p1);
    x += 1;
    // Bishops
    PieceBoard[y][x] = new Bishop(new Point(x, y), this, p2);
    PieceBoard[y][width - 1 - x] = new Bishop(new Point(width - 1 - x, y), this, p2);
    PieceBoard[height - 1 - y][x] = new Bishop(new Point(x, height - 1 - y), this, p1);
    PieceBoard[height - 1 - y][width - 1 - x] =
        new Bishop(new Point(width - 1 - x, height - 1 - y), this, p1);
    x += 1;
    // King
    PieceBoard[y][x] = new King(new Point(x, y), this, p2);
    PieceBoard[height - 1 - y][x] = new King(new Point(x, height - 1 - y), this, p1);
    x += 1;
    // Queen
    PieceBoard[y][x] = new Queen(new Point(x, y), this, p2);
    PieceBoard[height - 1 - y][x] = new Queen(new Point(x, height - 1 - y), this, p1);
    // Pawns
    for (int i = 0; i < width; i++) {
      PieceBoard[1][i] = new Pawn(new Point(i, 1), this, p2);
      PieceBoard[height - 2][i] = new Pawn(new Point(i, height - 2), this, p1);
    }
    // Empty spaces
    for (int i = 0; i < width; i++) {
      for (int j = 2; j < height - 2; j++) {
        PieceBoard[j][i] = new Empty(new Point(i, j), this);
      }
    }
  }

  @Override
  public Set<Move> getPossibleMoves(Player<Move> p) {
    Chess.Player chessPlayer = (Chess.Player) p;
    Set<Move> possibleMoves = new HashSet<>();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        for (Piece piece : chessPlayer.getCollection()) {
          Point from = piece.getCords();
          Point to = new Point(i, j);
          Move move = new Move(from, to, this);
          if (isValidMove(move, p)) {
            possibleMoves.add(move);
          }
        }
      }
    }
    return possibleMoves;
  }

  @Override
  public int customEvaluateFunction(Player<Move> p) {
    int score = 0;
    Chess.Player p1 = (Chess.Player) p;
    Chess.Player p2 = (Chess.Player) p.getOpponent();
    score += getPlayerCustomEval(p1);
    score -= getPlayerCustomEval(p2);
    return score;
  }

  private int getPlayerCustomEval(Chess.Player p) {
    int score = 0;
    for (Piece piece : p.getCollection()) {
      if (piece.isActive()) {
        switch (piece.getMark()) {
          case '*':
            score += 10000;
            break;
          case '^':
            score += 1000;
            break;
          case 'P':
            score += 10;
            break;
          case 'K':
            score += 50;
            break;
          case 'B':
            score += 75;
            break;
          case 'C':
            score += 25;
            break;
          default:
            score += 1;
            break;
        }
      }
    }
    return score;
  }
}
