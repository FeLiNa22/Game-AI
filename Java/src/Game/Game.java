package Game;

public class Game {

  private String name;
  private Board board;
  private Player currentPlayer;

  public Game(String name, Player p1, Player p2, Board board){
    this.name = name;
    p1.setOpponent(p2);
    p2.setOpponent(p1);
    p1.setMark('X');
    p2.setMark('O');
    this.currentPlayer = p1;
    this.board = board;
  }


  public void run() {
    System.out.println("You are Playing : " + name);
    board.drawBoard();
    while (board.getStatus(currentPlayer) == Status.PLAYING) {
      Move move = currentPlayer.getMove(board);
      while (!board.isValidMove(move,currentPlayer)) {
        System.out.println("That is an invalid move !!!");
        move = currentPlayer.getMove(board);
      }
      board.makeMove(move, currentPlayer);
      System.out.println(currentPlayer.getName() + " Has Moved");
      board.drawBoard();
      currentPlayer = currentPlayer.getOpponent();
    }
    System.out.println(getEndMessage());

  }

  private String getEndMessage() {
    switch (board.getStatus(currentPlayer)) {
      case WIN:
        return "THE WINNER IS " + currentPlayer.getName();
      case LOSE:
        return "THE WINNER IS " + currentPlayer.getOpponent().getName();
      case TIE:
        return "IT`S A TIE";
      default:
        return "";
    }
  }

}
