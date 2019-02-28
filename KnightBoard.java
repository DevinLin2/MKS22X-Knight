import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class KnightBoard {

  int[][] board;
  int[][] outgoingMoves;

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    board.solve(0,0);
    System.out.println(board);
    board.clear();
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
    return ans;
  }
  private void clear() {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        board[row][col] = 0;
      }
    }
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
    return optimizedSolveHelper(startingRow, startingCol, 1);
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
  private void debug() {
    String ans = "";
    if (board.length * board[0].length < 10) {
      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[r].length; c++) {
          ans += board[r][c] + " ";
        }
        ans += "\n";
      }
    } else {
      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[r].length; c++) {
          if (board[r][c] < 10) {
            ans += " " + board[r][c] + " ";
          } else {
            ans += board[r][c] + " ";
          }
        }
        ans += "\n";
      }
    }
    ans += "--------------------------------\n";
    if (outgoingMoves.length * outgoingMoves[0].length < 10) {
      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[r].length; c++) {
          ans += outgoingMoves[r][c] + " ";
        }
        ans += "\n";
      }
    } else {
      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[r].length; c++) {
          if (outgoingMoves[r][c] < 10) {
            ans += " " + outgoingMoves[r][c] + " ";
          } else {
            ans += outgoingMoves[r][c] + " ";
          }
        }
        ans += "\n";
      }
    }
    System.out.println(ans);
  }
  private boolean solveHelper(int row, int col, int level) {
    debug();
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

  // optimized solve using Warnsdorff's algorithm
  private boolean contains(int y, int x, ArrayList<Integer> moves) {
    if (moves.size() == 0) {
      return false;
    }
    for (int i = 0; i < moves.size(); i += 2) {
      if (y == moves.get(i) && x == moves.get(i+1)) {
        return true;
      }
    }
    return false;
  }
  private boolean optimizedSolveHelper(int row, int col, int level) {
    int[] moves = new int[] {2, 1, 2, -1, -2, 1, -2, -1, 1, 2, 1, -2, -1, 2, -1, -2};
    ArrayList<Integer> outgoingM = new ArrayList<Integer>();
    ArrayList<Integer> outgoingMoveCoords = new ArrayList<Integer>();
    int lowest = 0;
    int index = 0;
    //debug();
    if (level == board.length * board[0].length + 1) {
      return true;
    }
    for (int moveIndex = 0; moveIndex < moves.length; moveIndex += 2) {
      if (row + moves[moveIndex] < board.length && row + moves[moveIndex] >= 0 && col + moves[moveIndex + 1] < board[0].length && col + moves[moveIndex + 1] >= 0 &&
      outgoingMoves[row + moves[moveIndex]][col + moves[moveIndex + 1]] != 0 && board[row + moves[moveIndex]][col + moves[moveIndex + 1]] == 0) {
        outgoingM.add(outgoingMoves[row + moves[moveIndex]][col + moves[moveIndex + 1]]); // adds all the possible outgoing moves from each possible move to an ArrayList
      }
    }
    if (outgoingM.size() > 0) {
      Collections.sort(outgoingM);
      // makes an ArrayList of the coords prioritizing moves with lower outgoing moves
      for (int i = 0; i < outgoingM.size(); i++) {
        for (int moveIndex = 0; moveIndex < moves.length; moveIndex += 2) {
          if (row + moves[moveIndex] < board.length && row + moves[moveIndex] >= 0 && col + moves[moveIndex + 1] < board[0].length && col + moves[moveIndex + 1] >= 0 &&
          outgoingMoves[row + moves[moveIndex]][col + moves[moveIndex + 1]] == outgoingM.get(i) && !(contains(moves[moveIndex], moves[moveIndex + 1], outgoingMoveCoords))) {
            outgoingMoveCoords.add(moves[moveIndex]);
            outgoingMoveCoords.add(moves[moveIndex+1]);
          }
        }
      }
      for (int z = 0; z < outgoingMoveCoords.size(); z += 2) {
        if (addKnight(row, col, level) && optimizedSolveHelper(row + outgoingMoveCoords.get(z), col + outgoingMoveCoords.get(z+1), level + 1)) {
          return true;
        } else {
          removeKnight(row, col, level);
        }
      }
    } else {
      for (int z = 0; z < moves.length; z += 2) {
        if (addKnight(row, col, level) && optimizedSolveHelper(row + moves[z], col + moves[z+1], level + 1)) {
          return true;
        } else {
          removeKnight(row, col, level);
        }
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
}
