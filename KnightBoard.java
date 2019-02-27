import java.util.Arrays;

public class KnightBoard {

  int[][] board;
  int[][] outgoingMoves;

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println(board);
    board.addKnight(0,0,1);
    System.out.println(board);
    board.addKnight(2,1,2);
    System.out.println(board);
    board.removeKnight(2,1,2);
    System.out.println(board);
    board.removeKnight(0,0,1);
    System.out.println(board);
    // runTest(0);
    // runTest(1);
    // runTest(2);
    // runTest(3);
    // runTest(4);
  }

  public KnightBoard(int startingRows,int startingCols) {
    board = new int[startingRows][startingCols];
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        board[row][col] = 0;
      }
    }
    // initiates the board of possible moves to that space
    int[] moves = new int[] {2, 1, 2, -1, -2, 1, -2, -1, 1, 2, 1, -2, -1, 2, -1, -2};
    outgoingMoves = new int[startingRows][startingCols];
    int possibleMoves = 0;
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        for (int move = 0; move < moves.length; move += 2) { // loops through the possible moves to testhow many moves can go to this spot
          if (row + moves[move] < board.length && row + moves[move] >= 0 && col + moves[move + 1] < board[0].length && col + moves[move + 1] >= 0) {
            possibleMoves++;
          }
        }
        outgoingMoves[row][col] = possibleMoves;
        possibleMoves = 0;
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

    ans += "-----------------------------------------------------\n";
    // this prints out the possible outgoing moves from each space
    if (outgoingMoves.length * outgoingMoves[0].length < 10) {
      for (int row = 0; row < outgoingMoves.length; row++) {
        for (int col = 0; col < outgoingMoves[row].length; col++) {
          ans += outgoingMoves[row][col] + " ";
        }
        ans += "\n";
      }
    } else {
      for (int row = 0; row < outgoingMoves.length; row++) {
        for (int col = 0; col < outgoingMoves[row].length; col++) {
          if (outgoingMoves[row][col] < 10) {
            ans += " " + outgoingMoves[row][col] + " ";
          } else {
            ans += outgoingMoves[row][col] + " ";
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
    int[] moves = new int[] {2, 1, 2, -1, -2, 1, -2, -1, 1, 2, 1, -2, -1, 2, -1, -2};
    if (row < board.length && row >= 0 && col < board[0].length && col >= 0 && board[row][col] == 0) {
      board[row][col] = level;
      for (int moveIndex = 0; moveIndex < moves.length; moveIndex += 2) {
        if (row + moves[moveIndex] < board.length && row + moves[moveIndex] >= 0 && col + moves[moveIndex + 1] < board[0].length && col + moves[moveIndex + 1] >= 0) {
          outgoingMoves[row + moves[moveIndex]][col + moves[moveIndex + 1]] -= 1;
        }
      }
      return true;
    }
    return false;
  }
  private boolean removeKnight(int row, int col, int level) {
    int[] moves = new int[] {2, 1, 2, -1, -2, 1, -2, -1, 1, 2, 1, -2, -1, 2, -1, -2};
    if (row < board.length && row >= 0 && col < board[0].length && col >= 0 && board[row][col] == level) {
      board[row][col] = 0;
      for (int moveIndex = 0; moveIndex < moves.length; moveIndex += 2) {
        if (row + moves[moveIndex] < board.length && row + moves[moveIndex] >= 0 && col + moves[moveIndex + 1] < board[0].length && col + moves[moveIndex + 1] >= 0) {
          outgoingMoves[row + moves[moveIndex]][col + moves[moveIndex + 1]] += 1;
        }
      }
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
  public int countSolutionsHelper(int r, int c, int level) {
    int[] moves = new int[] {2, 1, 2, -1, -2, 1, -2, -1, 1, 2, 1, -2, -1, 2, -1, -2};
    int ans = 0;
    if (level == board.length * board[0].length) {
      return 1;
    }
    addKnight(r, c, level);
    for (int moveIndex = 0; moveIndex < moves.length; moveIndex += 2) {
      if (r + moves[moveIndex] < board.length && r + moves[moveIndex] >= 0 &&
          c + moves[moveIndex + 1] < board[0].length && c + moves[moveIndex + 1] >= 0 &&
          board[r + moves[moveIndex]][c + moves[moveIndex + 1]] == 0) {
        ans += countSolutionsHelper(r + moves[moveIndex], c + moves[moveIndex+1], level + 1);
      }
    }
    removeKnight(r, c, level);
    return ans;
  }
  public static void runTest(int i){
    KnightBoard b;
    int[]m =   {4,5,5,5,5};
    int[]n =   {4,5,4,5,5};
    int[]startx = {0,0,0,1,2};
    int[]starty = {0,0,0,1,2};
    int[]answers = {0,304,32,56,64};
    if(i >= 0 ){
      try{
        int correct = answers[i];
        b = new KnightBoard(m[i%m.length],n[i%m.length]);
        int ans  = b.countSolutions(startx[i],starty[i]);
        if(correct==ans){
          System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
        }else{
          System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
        }
      }catch(Exception e){
        System.out.println("FAIL Exception case: "+i);
      }
    }
  }
}
