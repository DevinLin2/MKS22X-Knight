import java.util.Arrays;

public class KnightBoard {

  int[][] board;

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println(board.solve(0,0));
    System.out.println(board);
  }

  public KnightBoard(int startingRows,int startingCols) {
    board = new int[startingRows][startingCols];
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
    return solveHelper(startingRow, startingCol, 1);
  }
  private boolean addKnight(int row, int col, int level) {
    if (row < board.length && row >= 0 && col < board[0].length && col >= 0 && board[row][col] == 0) {
      board[row][col] = level;
      return true;
    }
    return false;
  }
  private boolean removeKnight(int row, int col, int level) {
    if (row < board.length && row >= 0 && col < board[0].length && col >= 0 && board[row][col] == level) {
      board[row][col] = 0;
      return true;
    }
    return false;
  }
  private boolean solveHelper(int row, int col, int level) {
    int[] moves = new int[] {2, 1, 2, -1, -2, 1, -2, -1, 1, 2, 1, -2, -1, 2, -1, -2};
    if (level == board.length * board[0].length + 1) {
      return true;
    }
    for (int i = 0; i < moves.length; i += 2) {
      if (addKnight(row, col, level) && solveHelper(row + moves[i], col + moves[i+1], level + 1)) {
        return true;
      } else {
        removeKnight(row, col, level);
      }
    }
    return false;
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
    return countSolutionsHelper(startingRow, startingCol, 1);
  }
  public int countSolutionsHelper(int row, int col, int level) {
    int[] moves = new int[] {2, 1, 2, -1, -2, 1, -2, -1, 1, 2, 1, -2, -1, 2, -1, -2};
    int ans = 0;
    if (level == board.length * board[0].length + 1) {
      return 1;
    }
    for (int i = 0; i < moves.length; i += 2) {
      if (addKnight(row, col, level)) {
        return ans += countSolutionsHelper(row + moves[i], col + moves[i+1], level + 1);
      } else {
        removeKnight(row, col, level);
      }
    }
    return ans;
  }
}
