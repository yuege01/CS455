// Name:Ziwei Fang
// USC NetID:ziweifan
// CSCI455 PA2
// Fall 2018

import java.util.ArrayList;
import java.util.Random;
/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   
   private static final int ARRAY_SIZE = 2 * CARD_TOTAL;
   
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   
   /**
      Representation invariant:
      CARD_TOTAL should be calculated by NUM_FINAL_PILES
      The sum of all numbers in each pile should be equal to CARD_TOTAL
      The number in each pile can never be a negative
      Size of the Cards can never be larger than CARD_TOTAL
      <put rep. invar. comment here>

   */
   private int[] cards;
   private int size;
   private Random random;
   // <add instance variables here>
  
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
      cards = new int[ARRAY_SIZE];
      size = piles.size();
      for(int i = 0; i < size; i++) {
         cards[i] = piles.get(i);
      }

      assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
         random = new Random();
         ArrayList<Integer> res = new ArrayList<Integer> ();
         int sum = this.CARD_TOTAL;
         int piles = 0;
         while(sum > 0 && piles < CARD_TOTAL) {
            int temp = random.nextInt(sum) + 1;
            res.add(temp);
            sum = sum - temp;
            piles++;
         }
         if(sum > 0) {
            res.add(sum);
         }
          cards = new int[ARRAY_SIZE];
          size = res.size();
         for(int i = 0; i < size; i++) {
            cards[i] = res.get(i);
         }
         
         assert isValidSolitaireBoard();
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
      /* Use only constant space variables and one loop, thus realizing
       * a O(1)space complexity and O(n) time complexity
       */
      int sum = 0;
      for(int i = 0; i < size; i++) {
         if(cards[i] != 0) {
            cards[i]--;;
            sum++;
         }
      }
      cards[size++] = sum;
      
      assert isValidSolitaireBoard();
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */
   
   public boolean isDone() {
      // using a piles array to store the cards, which cost O(n) space
      // then using one loop to determine whether the game is done.
      // thus the space complexity is O(n), the time complexity is O(n);
      assert isValidSolitaireBoard();
      boolean[] piles = new boolean[NUM_FINAL_PILES + 1];
      for(int i = 0; i < size; i++) {
         if(cards[i] == 0) {
            continue;
         }
         if(cards[i] > NUM_FINAL_PILES || piles[cards[i]] == true) {
            return false;
         }
         piles[cards[i]] = true;
      }
      
      return true;
      // dummy code to get stub to compile
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
      String result = new String();
      for(int i = 0; i < size; i++) {
         if(cards[i] != 0) {
            result = result + cards[i] + " ";
         }
      }
      result = result.trim();
      
      assert isValidSolitaireBoard();
      return result; // dummy code to get stub to compile
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
      int sum = 0;
      for(int i = 0; i < size; i++) {
         if(cards[i] > CARD_TOTAL) {
            return false;
         }
         sum += cards[i];
      }
      
      return sum == CARD_TOTAL;  // dummy code to get stub to compile

   }
   

   // <add any additional private methods here>


}
