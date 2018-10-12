// Name:Ziwei Fang
// USC NetID:ziweifan
// CS 455 PA1
// Fall 2018

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;

public class CoinTossSimulator {
	private int trials;
	private int twoHeads;
	private int twoTails;
	private int headTails;



   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   trials = 0;
	   twoHeads = 0;
	   twoTails = 0;
	   headTails = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      twoHeads = 0;
      twoTails = 0;
      headTails = 0;
      trials += numTrials;
      Random rand = new Random();
      for(int i = 0; i < trials; i++) {
		   int coin1 = rand.nextInt(2);
		   int coin2 = rand.nextInt(2);
		   if(coin1 == 1 && coin2 == 1)
			   twoHeads++;
		   else if(coin1 == 0 && coin2 == 0)
			   twoTails++;
		   else headTails++;
	   }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return trials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return twoHeads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return twoTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return headTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	  trials = 0;
	  twoHeads = 0;
	  twoTails = 0;
	  headTails = 0;
   }

}
