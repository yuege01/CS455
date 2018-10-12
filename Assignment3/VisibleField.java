// Name:Ziwei Fang
// USC NetID:ziweifan
// CS 455 PA3
// Fall 2018

import java.util.Arrays;
/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to moves the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   private int[][] covered;
   private MineField currentState;
   private int guesses;
   private int explored;
   private boolean isFailed;
   

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      covered = new int[mineField.numRows()][mineField.numCols()];
      for(int i = 0; i < mineField.numRows(); i++) {
        for(int j = 0; j < mineField.numCols(); j++) {
          covered[i][j] = COVERED;
        }
      }
      currentState = mineField;
      guesses = 0;
      explored = 0;
      isFailed = false;
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying MineField. 
   */     
   public void resetGameDisplay() {
    for(int i = 0; i < covered.length; i++) {
        for(int j = 0; j < covered[0].length; j++) {
          covered[i][j] = COVERED;
        }
      }
      getMineField().resetEmpty();
      guesses = 0;
      explored = 0;
      isFailed = false;
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return currentState;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      get the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
    assert getMineField().inRange(row, col);
    return covered[row][col];

   }

   
   /**
      Return the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  So the value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      return getMineField().numMines() - guesses;       // DUMMY CODE so skeleton compiles

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      assert getMineField().inRange(row, col);
      if(getStatus(row, col) == COVERED) {
        covered[row][col] = MINE_GUESS;
        guesses++;
      }
      else if(getStatus(row, col) == MINE_GUESS) {
        covered[row][col] = QUESTION;
        guesses--;
      }
      else if(getStatus(row, col) == QUESTION) {
        covered[row][col] = COVERED;
      }
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      assert getMineField().inRange(row, col);
      if(getMineField().hasMine(row, col)) {
        explored++;
        covered[row][col] = EXPLODED_MINE;
        isFailed = true;
        return false;
      }
      else {
        dfs(row, col);
      }
      return true;
   }
 
   
   /**
      Returns whether the game is over.
      @return whether game over
    */
   public boolean isGameOver() {
      int rows = getMineField().numRows();
      int cols = getMineField().numCols();
      int mines = getMineField().numMines();
      if(isFailed) {
        for(int i = 0; i < rows; i++) {
          for(int j = 0; j < cols; j++) {
            if(getStatus(i, j) == MINE_GUESS && !getMineField().hasMine(i, j)) {
              covered[i][j] = INCORRECT_GUESS;
            }
            else if(getStatus(i, j) == COVERED && getMineField().hasMine(i, j)) {
              covered[i][j] = MINE;
            }
          }
        }
        return true;
      }
      if(explored == rows * cols - mines && !isFailed) {
        for(int i = 0; i < rows; i++) {
          for(int j = 0; j < cols; j++) {
            if(getStatus(i, j) == COVERED) {
              covered[i][j] = MINE_GUESS;
            }
          }
        }
        return true;
      }
      return false;
   }
 
   
   /**
      Return whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      assert getMineField().inRange(row, col);
      int status = getStatus(row, col);
      if(status == COVERED || status == MINE_GUESS || status == QUESTION) {
        return false;
      }
      return true;
   // <put private methods here>
    }

    private void dfs (int row, int col) {
      if(!getMineField().inRange(row, col) || (getStatus(row, col) == MINE_GUESS) || getMineField().hasMine(row, col)) {
        return;
      }
      if(isUncovered(row, col)) {
        return;
      }
      covered[row][col] = getMineField().numAdjacentMines(row, col);
      explored++;
      if(getStatus(row, col) == 0) {
        dfs(row + 1, col + 1);
        dfs(row + 1, col - 1);
        dfs(row, col + 1);
        dfs(row, col - 1);
        dfs(row + 1, col);
        dfs(row - 1, col);
        dfs(row - 1, col - 1);
        dfs(row - 1, col + 1);
      }
    }
   
}
