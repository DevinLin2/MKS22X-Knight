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
   * @throws IllegalStateException when the board contains non-zero values.
   * @throws IllegalArgumentException when either parameter is negative or out of bounds.
   */
  public boolean solve(int startingRow, int startingCol) {

  }
  /**
   * @throws IllegalStateException when the board contains non-zero values.
   * @throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public int countSolutions(int startingRow, int startingCol) {

  }
}
