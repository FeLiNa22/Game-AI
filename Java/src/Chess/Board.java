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

  public Piece getPiece(Cords cords) {
    return PieceBoard[cords.getY()][cords.getX()];
  }

  @Override
  public boolean hasTied(Player<Move> p) {
    return false;
  }

  @Override
  public boolean hasWon(Player<Move> p) {
    return false;
  }

  @Override
  public boolean hasLost(Player<Move> p) {
    return false;
  }

  @Override
  public boolean isValidMove(Move move, Player<Move> p) {
    Cords from = move.getFrom();
    Cords to = move.getTo();
    if (isInBounds(from) && isInBounds(to) && !isBlocked(to, p) && !to.equals(from)) {
      return getPiece(from).validMove(to);
    } else {
      return false;
    }
  }

  private boolean isBlocked(Cords cords, Player<Move> p) {
    if (isInBounds(cords)) {
      return getPiece(cords).getAssignedPlayer() == p;
    }
    return false;
  }

  private boolean isInBounds(Cords cords) {
    int x = cords.getX();
    int y = cords.getY();
    return (x >= 0 && x < width && y >= 0 && y < height);
  }

  @Override
  public void makeMove(Move move, Player<Move> p) {
    moves.push(move);
    Cords to = move.getTo();
    Cords from = move.getFrom();
    getPiece(from).moveTo(to);
    PieceBoard[to.getY()][to.getX()] = PieceBoard[from.getY()][from.getX()];
    PieceBoard[from.getY()][from.getX()] = new Empty(from, this);
    move.getPiece().deactivate();
  }

  @Override
  public void undoMove(Player<Move> p) {
    Move move = moves.poll();
    assert move != null;
    Cords to = move.getTo();
    Cords from = move.getFrom();
    getPiece(to).moveTo(from);
    PieceBoard[from.getY()][from.getX()] = PieceBoard[to.getY()][to.getX()];
    PieceBoard[to.getY()][to.getX()] = move.getPiece();
    move.getPiece().activate();
  }

  @Override
  public void initialiseBoard(Player<Move> p1, Player<Move> p2) {
    int x = 0;
    int y = 0;
    // Castles
    PieceBoard[y][x] = new Castle(new Cords(x, y), this, (Chess.Player) p2);
    PieceBoard[y][width - 1 - x] = new Castle(new Cords(width - 1 - x, y), this, (Chess.Player) p2);
    PieceBoard[height - 1 - y][x] = new Castle(new Cords(x, height - 1 - y), this,
        (Chess.Player) p1);
    PieceBoard[height - 1 - y][width - 1 - x] = new Castle(new Cords(width - 1 - x, height - 1 - y),
        this, (Chess.Player) p1);
    x += 1;
    // Knights
    PieceBoard[y][x] = new Knight(new Cords(x, y), this, (Chess.Player) p2);
    PieceBoard[y][width - 1 - x] = new Knight(new Cords(width - 1 - x, y), this, (Chess.Player) p2);
    PieceBoard[height - 1 - y][x] = new Knight(new Cords(x, height - 1 - y), this,
        (Chess.Player) p1);
    PieceBoard[height - 1 - y][width - 1 - x] = new Knight(new Cords(width - 1 - x, height - 1 - y),
        this, (Chess.Player) p1);
    x += 1;
    // Bishops
    PieceBoard[y][x] = new Bishop(new Cords(x, y), this, (Chess.Player) p2);
    PieceBoard[y][width - 1 - x] = new Bishop(new Cords(width - 1 - x, y), this, (Chess.Player) p2);
    PieceBoard[height - 1 - y][x] = new Bishop(new Cords(x, height - 1 - y), this,
        (Chess.Player) p1);
    PieceBoard[height - 1 - y][width - 1 - x] = new Bishop(new Cords(width - 1 - x, height - 1 - y),
        this, (Chess.Player) p1);
    x += 1;
    // King
    PieceBoard[y][x] = new King(new Cords(x, y), this, (Chess.Player) p2);
    PieceBoard[height - 1 - y][x] = new King(new Cords(x, height - 1 - y), this, (Chess.Player) p1);
    x += 1;
    // Queen
    PieceBoard[y][x] = new Queen(new Cords(x, y), this, (Chess.Player) p2);
    PieceBoard[height - 1 - y][x] = new Queen(new Cords(x, height - 1 - y), this,
        (Chess.Player) p1);
    // Pawns
    for (int i = 0; i < width; i++) {
      PieceBoard[1][i] = new Pawn(new Cords(i, 1), this, (Chess.Player) p2);
      PieceBoard[height - 2][i] = new Pawn(new Cords(i, height - 2), this, (Chess.Player) p1);
    }
    // Empty spaces
    for (int i = 0; i < width; i++) {
      for (int j = 2; j < height - 2; j++) {
        PieceBoard[j][i] = new Empty(new Cords(i, j), this);
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
          Cords from = piece.getCords();
          Cords to = new Cords(i, j);
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
    for (Piece piece : p1.getCollection()) {
      if (piece.isActive()) {
        score += 10;
      }
    }
    for (Piece piece : p2.getCollection()) {
      if (piece.isActive()) {
        score -= 10;
      }
    }
    return score;
  }
}
