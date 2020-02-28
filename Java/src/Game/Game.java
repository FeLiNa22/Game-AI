package Game;

public class Game<S> {

  private String name;
  private Board<S> board;
  private Player<S> currentPlayer;

  public Game(String name, Player<S> p1, Player<S> p2, Board<S> board){
    this.name = name;
    p1.setOpponent(p2);
    p2.setOpponent(p1);
    p1.setMark('X');
    p2.setMark('O');
    this.currentPlayer = p1;
    this.board = board;
    board.initialiseBoard(p1,p2);
  }


  public void run() {
    System.out.println("You are Playing : " + name);
    board.drawBoard();
    while (board.getStatus(currentPlayer) == Status.PLAYING) {
      S move = currentPlayer.getMove(board);
      while (!board.isValidMove(move,currentPlayer)) {
        System.out.println("That is an invalid move !!!");
        move = currentPlayer.getMove(board);
      }
      board.makeMove(move, currentPlayer);
      System.out.println(currentPlayer.getName() + " Has Moved " + move);
      board.drawBoard();
      currentPlayer = currentPlayer.getOpponent();
    }
    System.out.println(getEndMessage());

  }

  private String getEndMessage() {
    switch (board.getStatus(currentPlayer)) {
      case WIN:
        return "THE WINNER IS " + currentPlayer.getName() + " - " + currentPlayer.getMark();
      case LOSE:
        return "THE WINNER IS " + currentPlayer.getOpponent().getName() + " - " + currentPlayer.getOpponent().getMark();
      case TIE:
        return "IT`S A TIE";
      default:
        return "";
    }
  }

}
