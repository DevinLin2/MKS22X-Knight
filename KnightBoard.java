public class KnightBoard {
  int[][] board;

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println(solve(0,0));
    System.out.println(board);
  }

  public KnightBoard(int startingRows,int startingCols) {
    board = new board[startingRows][startingCols];
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        board[row][col] = 0;
      }
    }
  }

  public String toString() {
    String ans = "";
    if (board.length * board[0].length < 10) {
      for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board[row].length; col++) {
          ans += board[row][col] + " ";
        }
        ans += "\n";
      }
    } else {
      for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board[row].length; col++) {
          if (board[row][col] < 10) {
            ans += " " + board[row][col] + " ";
          } else {
            ans += board[row][col] + " ";
          }
        }
        ans += "\n";
      }
    }
    return ans;
  }
  /**
   * Attempts to solve the Knights Tour given the starting row and col
   * @param startingRow the starting row
   * @param startingCol the starting col
   * @throws IllegalStateException when the board contains non-zero values.
   * @throws IllegalArgumentException when either parameter is negative or out of bounds.
   */
  public boolean solve(int startingRow, int startingCol) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] != 0) {
          throw new IllegalStateException("The board contains non-zero values");
        }
      }
    }
    if (startingCol < 0 || startingRow < 0) {
      throw new IllegalArgumentException("Parameter(s) cannot be nagative");
    }
    if (startingRow > board.length-1 || startingCol > board[0].length-1){
      throw new IllegalArgumentException("Parameter(s) exceed array length");
    }
    return solveHelper(startingRow, startingCol, 0);
  }
  private boolean solveHelper(int row, int col, int level) {
    if (level == board.length * board[0].length) {
      return true;
    }
    board[row][col] = level;
    /*
    valid moves (xMove, yMove):
    (2,1)
    (2,-1)
    (-2,1)
    (-2,-1)
    (-1,2)
    (-1,-2)
    (1,2)
    (1,-2)
    */
    if ((validMove(row, col, 2, 1) && solveHelper(row+2, col+1)) ||
        (validMove(row, col, 2, -1) && solveHelper(row+2, col-1)) ||
        (validMove(row, col, -2, 1) && solveHelper(row-2, col+1)) ||
        (validMove(row, col, -2, -1) && solveHelper(row-2, col-1)) ||
        (validMove(row, col, -1, 2) && solveHelper(row-1, col+2)) ||
        (validMove(row, col, -1, -2) && solveHelper(row-1, col-2)) ||
        (validMove(row, col, 1, 2) && solveHelper(row+1, col+2)) ||
        (validMove(row, col, 1, -2) && solveHelper(row+1, col-2)) ||) {
      return true;
    }
    board[row][col] = 0;
    return false;
  }
  private boolean validMove(int knightY, int knightX, int xMove, int yMove) {
    if (board[knightY+yMove][knightX+xMove] != 0 && knightX + xMove < board[0].length && knightX + xMove > 0 && KnightY + yMove < board.length && knightY + yMove > 0) {
      return true;
    }
  }
  /**
   * Attempts to count all the solutions of the Knights Tour given the starting row and col
   * @param startingRow the starting row
   * @param startingCol the starting col
   * @throws IllegalStateException when the board contains non-zero values.
   * @throws IllegalArgumentException when either parameter is negative or out of bounds.
   */
  public int countSolutions(int startingRow, int startingCol) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] != 0) {
          throw new IllegalStateException("The board contains non-zero values");
        }
      }
    }
    if (startingCol < 0 || startingRow < 0) {
      throw new IllegalArgumentException("Parameter(s) cannot be nagative");
    }
    if (startingRow > board.length-1 || startingCol > board[0].length-1){
      throw new IllegalArgumentException("Parameter(s) exceed array length");
    }
  }
}
