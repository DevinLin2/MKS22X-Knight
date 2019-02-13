public class KnightBoard {
  int[][] board;

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
    
  }
  private boolean validMove(int knightX, int knightY, int xMove, int yMove) {
    if (knightX + xMove < board[0].length && knightX + xMove > 0 && KnightY + yMove < board.length && knightY + yMove > 0) {
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
