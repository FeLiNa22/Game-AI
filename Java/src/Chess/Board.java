package Chess;

import Chess.Pieces.Bishop;
import Chess.Pieces.Castle;
import Chess.Pieces.Empty;
import Chess.Pieces.King;
import Chess.Pieces.Knight;
import Chess.Pieces.Pawn;
import Chess.Pieces.Queen;
import Game.Player;
import Utils.Tuple;
import java.util.Set;

public class Board extends Game.Board<Move> {
  private Piece[][] PieceBoard;

  public Board() {
    super(8, 8);
    this.PieceBoard = new Piece[8][8];
    clearBoard();
  }

  @Override
  public void drawBoard() {
    System.out.println();
    for (int i = 0; i < height; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < width; j++) {
        System.out.print(PieceBoard[i][j].getMark() + " ");
      }
      System.out.println();
    }
    System.out.print("a b c d e f g h");
    for (int t = 0; t < width; t++) {
      System.out.print("--");
    }
    System.out.println();
  }

  public Piece getPiece(Tuple<Integer, Integer> cords) {
    return PieceBoard[cords.getFirst()][cords.getSecond()];
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
  public boolean isValidMove(Move move, Player p) {
    return false;
  }

  @Override
  public void makeMove(Move move, Player p) {
    moves.push(move);
    Tuple<Integer, Integer> to = move.getTo();
    Tuple<Integer, Integer> from = move.getFrom();
    PieceBoard[to.getSecond()][to.getFirst()] = PieceBoard[from.getSecond()][from.getFirst()];
    PieceBoard[from.getSecond()][from.getFirst()] =
        new Empty(from.getFirst(), from.getSecond(), this, (Chess.Player) p);
  }

  @Override
  public void undoMove(Player p) {
    Move move = moves.poll();
    assert move != null;
    Tuple<Integer, Integer> to = move.getTo();
    Tuple<Integer, Integer> from = move.getFrom();
    PieceBoard[from.getSecond()][from.getFirst()] = PieceBoard[to.getSecond()][to.getFirst()];
    PieceBoard[to.getSecond()][to.getFirst()] = move.getPiece();
  }

  @Override
  public void initialiseBoard(Player p1, Player p2) {
    int x = 0;
    int y = 0;
    // Castles
    PieceBoard[y][x] = new Castle(x, y, this, p2);
    PieceBoard[y][width - 1 - x] = new Castle(x, y, this, p2);
    PieceBoard[height - 1 - y][x] = new Castle(x, y, this, p1);
    PieceBoard[height - 1 - y][width - 1 - x] = new Castle(x, y, this, p1);
    x += 1;
    // Knights
    PieceBoard[y][x] = new Knight(x, y, this, p2);
    PieceBoard[y][width - 1 - x] = new Knight(x, y, this, p2);
    PieceBoard[height - 1 - y][x] = new Knight(x, y, this, p1);
    PieceBoard[height - 1 - y][width - 1 - x] = new Knight(x, y, this, p1);
    x += 1;
    // Bishops
    PieceBoard[y][x] = new Bishop(x, y, this, p2);
    PieceBoard[y][width - 1 - x] = new Bishop(x, y, this, p2);
    PieceBoard[height - y][x] = new Bishop(x, y, this, p1);
    PieceBoard[height - y][width - 1 - x] = new Bishop(x, y, this, p1);
    x += 1;
    // King
    PieceBoard[y][x] = new King(x, y, this, p2);
    PieceBoard[height - 1 - y][x] = new King(x, y, this, p1);
    x += 1;
    // Queen
    PieceBoard[y][x] = new Queen(x, y, this, p2);
    PieceBoard[height - 1 - y][x] = new Queen(x, y, this, p1);
    // Pawns
    for (int i = 0; i < width; i++) {
      PieceBoard[1][i] = new Pawn(i, 1, this, p1);
      PieceBoard[height - 2][i] = new Pawn(i, height - 2, this, p2);
    }
  }

  @Override
  public Set<Move> getPossibleMoves(Player p) {
    return null;
  }
}
