// Name:Ziwei Fang
// USC NetID:ziweifan
// CS 455 PA3
// Fall 2018

import java.util.Random;
import java.util.Arrays;
/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
  private static final int UP = -1;
  private static final int DOWN = 1;
  private static final int LEFT = -1;
  private static final int RIGHT = 1;
   
   // <put instance variables here>
   private boolean[][] mineField;
   private int numMines;
   private Random random;
   
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
    * @param mineData  the data for the mines; must have at least one row and one col.
    */
   public MineField(boolean[][] mineData) {
      random = new Random();
      mineField = Arrays.copyOf(mineData, mineData.length);
      numMines = 0;
      for(int i = 0; i < mineField.length; i++) {
        for(int j = 0; j < mineField[0].length; j++) {
          if(mineField[i][j]) {
            numMines++;
          }
        }
      }
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      assert numRows > 0 && numCols > 0;
      int limit = numRows * numCols; 
      assert numMines < limit / 3.0;
      random = new Random();
      mineField = new boolean[numRows][numCols];
      this.numMines = numMines;
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col)
    */
   public void populateMineField(int row, int col) {
      assert inRange(row, col);
      int remainMines = numMines();
      while(remainMines > 0) {
        int rowIndex = random.nextInt(numRows());
        int colIndex = random.nextInt(numCols());
        if(!mineField[rowIndex][colIndex] && rowIndex != row && colIndex != col) {
          mineField[rowIndex][colIndex] = true;
          remainMines--;
        }
      }
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state the minefield is in at the beginning of a game.
    */
   public void resetEmpty() {
      mineField = new boolean[numRows()][numCols()];
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      assert inRange(row, col);
      int ajacentMines = 0;       // DUMMY CODE so skeleton compiles
      int startRow = row + UP;
      int endRow = row + DOWN;
      int startCol = col + LEFT;
      int endCol = col + RIGHT;
      for(int i = startRow; i <= endRow; i++) {
        for(int j = startCol; j <= endCol; j++) {
          if(inRange(i, j) && mineField[i][j] && (i != row || j != col)) {
            ajacentMines++;
          }
      }
    }
    return ajacentMines;
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
    if(row < 0 || row >= numRows() || col < 0 || col >= numCols()) {
      return false;    
    }
    return true;
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return mineField.length;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */    
   public int numCols() {
      return mineField[0].length;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      assert inRange(row, col);
      return mineField[row][col];  
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return numMines;       // DUMMY CODE so skeleton compiles
   }

   
   // <put private methods here>
   
         
}

