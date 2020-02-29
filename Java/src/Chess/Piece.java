package Chess;


public abstract class Piece implements PieceTemplate {

  protected Point point;
  protected Character mark;
  protected Board board;
  protected Player assignedPlayer;
  private boolean active;
  protected boolean firstMove;

  public Piece(Point point, Board board, Player player) {
    this.point = point;
    this.board = board;
    assignedPlayer = player;
    player.addToCollection(this);
    mark = '.';
    firstMove = true;
    active = true;
  }

  public boolean isFirstMove() {
    return firstMove;
  }

  public void setFirstMove(boolean firstMove) {
    this.firstMove = firstMove;
  }

  public Player getAssignedPlayer() {
    return assignedPlayer;
  }

  public Character getMark() {
    return mark;
  }

  public void setMark(Character mark) {
    this.mark = mark;
  }

  public Point getCords() {
    return point;
  }

  public void updateCords(Point point) {
    this.point = point;
  }

  public void activate() {
    active = true;
  }

  public void deactivate() {
    active = false;
  }

  public boolean isActive() {
    return active;
  }

  public boolean checkVertical(Move move) {
    Point from = move.getFrom();
    Point to = move.getTo();
    if (from.getX() == to.getX()) {
      if (from.getY() <= to.getY()) {
        return validateSteps(move,new Point(0, 1));
      } else {
        return validateSteps(move,new Point(0, -1));
      }
    } else {
      return false;
    }
  }

  public boolean checkHorizontal(Move move) {
    Point from = move.getFrom();
    Point to = move.getTo();
    if (from.getY() == to.getY()) {
      if (from.getX() <= to.getX()) {
        return validateSteps(move,new Point(1, 0));
      } else {
        return validateSteps(move,new Point(-1, 0));
      }
    } else {
      return false;
    }
  }

  public boolean checkMainDiagonal(Move move) {
    Point from = move.getFrom();
    Point to = move.getTo();
    if(to.getX() - from.getX() == 0){
      return false;
    }
    if (((float) (to.getY() - from.getY()) / (to.getX() - from.getX())) == 1) {
      if (from.getY() <= to.getY()) {
        return validateSteps(move,new Point(1, 1));
      } else {
        return validateSteps(move,new Point(-1, -1));
      }
    } else {
      return false;
    }
  }

  public boolean checkSecondDiagonal(Move move) {
    Point from = move.getFrom();
    Point to = move.getTo();
    if(to.getX() - from.getX() == 0){
      return false;
    }
    if (((float) (to.getY() - from.getY()) / (to.getX() - from.getX())) == -1) {
      if (from.getY() <= to.getY()) {
        return validateSteps(move,new Point(-1, 1));
      } else {
        return validateSteps(move,new Point(1, -1));
      }
    } else {
      return false;
    }
  }

   private boolean validateSteps(Move move, Point dir) {
    Point point = move.getFrom();
     while (true) {
       point = point.add(dir);
       if(point.equals(move.getTo())){
         break;
       }
       if (board.getPiece(point).getMark() != '.') {
        return false;
      }
    }
    return true;
  }
}
