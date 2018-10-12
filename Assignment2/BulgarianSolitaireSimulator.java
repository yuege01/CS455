// Name:Ziwei Fang
// USC NetID:ziweifan   
// CSCI455 PA2
// Fall 2018


/**
 * Class BulgarianSolitaireSimulator
 *
 * Simulates Bulgarian Solitaire Game and diplays the result.
 * by using different Commands line user can control whether to provide input
 * themselves and then display results to the end, or generate a random input and
 * and show the result step by step.
 * Type -u to run the program with user inputs, type -s to run program with random generated inputs
 * and show the result step by step when user hits enter.
 * Type nothing to run program with randon generated inputs and show results without stopping.
   <add main program comment here>
*/
import java.util.Scanner;
import java.util.ArrayList;
public class BulgarianSolitaireSimulator {
   /**
    * Main Method, the tester of the application.
    *
    * @param args argument list
    *             -u Prompts the user for the initial input and print results in a whole
    *             -s Generate a random input and show the result step by step
   */

   public static void main(String[] args) {
     
      boolean singleStep = false;
      boolean userConfig = false;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
         
      }
      Scanner in = new Scanner(System.in);
      ArrayList<Integer> res = new ArrayList<Integer>();
      if(userConfig) {
         testUser(res, in, singleStep);
      }
      if(singleStep) {
         testSingle(in, singleStep);
      }
      if(!userConfig && !singleStep) {
         testSingle(in, singleStep);
      }
   }

  /**
    * The helper function to read inputs from the user and do the error checking on the inputs
    * Construct an ArrayList object for the following construction of a solitaireBoard
   */
   private static boolean helper(ArrayList<Integer> res, Scanner in) {
      System.out.println("Please enter a space-separated list of positive integers followed by newline:");
      String line = in.nextLine();
      int sum = 0;
      String s = "";
      for(int i = 0; i < line.length(); i++) {
         if((line.charAt(i) != ' ') && (line.charAt(i) != '\t') && (!Character.isDigit(line.charAt(i)))) {
            return false;
         }
         else {
            s = s + line.charAt(i);
         }
      }
      Scanner lineScanner = new Scanner(s);
      while(lineScanner.hasNext()) {
         int temp = lineScanner.nextInt();
         if(temp <= 0) {
            return false;
         }
         res.add(temp);
         sum += temp;
      }
      if(sum != SolitaireBoard.CARD_TOTAL) {
         return false;
      }
      return true;
   }
   /**
    * Tester the results of the SolitaireBoard Game 
    * @param singleStep  examine the commandline from user.
   */
   private static void tester(SolitaireBoard game, boolean singleStep, Scanner in) {
      int i = 1;
      while(!game.isDone()) {
         game.playRound();
         System.out.println("[" + i + "] " + "Current configuration: " + game.configString());
         i++;
         if(singleStep) {
            System.out.print("<Type return to continue>");
            in.nextLine();
         }

      }
      System.out.println("Done!");
   }
   /* Test the result when the command line is -s.
    * Under the case, the program will generate a random input and print result one by one
   */
   private static void testSingle(Scanner in, boolean singleStep) {
         SolitaireBoard game2 = new SolitaireBoard();
         System.out.println("Initial configuration: " + game2.configString());
         tester(game2, singleStep, in);
   }
   /* Test the result when the command line is -u
    * Under the case, the progrm will prompt the user for inputs
    * If the input of the user is not valid, it will print error-message to prompt to enter again
    */
   private static void testUser(ArrayList<Integer> res, Scanner in, boolean singleStep) {
         System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
         System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
         boolean isValid = helper(res, in);
         while(!isValid) {
               res = new ArrayList<Integer>();
               System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
               isValid = helper(res, in);
         }
         SolitaireBoard game = new SolitaireBoard(res);
         System.out.println("Initial configuration: " + game.configString());
         tester(game, singleStep, in);
   }
   
   // <add private static methods here>

}
