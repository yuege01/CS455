/**
 * CoinTossSimulatorTester class
 * 
 * Test my CoinTossSimulator class independently from its use in the 
 * CoinSimViewer program
 * 
 */

public class CoinTossSimulatorTester {
	public static void main(String[] args) {
		
		CoinTossSimulator test = new CoinTossSimulator();
		int trials = test.getNumTrials();
		int heads = test.getTwoHeads();
		int tails = test.getTwoTails();
		int headTails = test.getHeadTails();
		System.out.println("After constructor: ");
		System.out.println("Number of trials [exp:0]: " + trials);
		System.out.println("Two-head tosses: " + heads);
		System.out.println("Two-tail tosses: " + tails);
		System.out.println("One-head one-tail tosses: " + headTails);
		System.out.println("Tosses add up correctly? " + (trials == (tails + heads + headTails)));
		
		test.run(1);
		trials = test.getNumTrials();
		heads = test.getTwoHeads();
		tails = test.getTwoTails();
		headTails = test.getHeadTails();
		System.out.println("After run" + "(" + 1 + "):");
		System.out.println("Number of trials [exp:1]: " + trials);
		System.out.println("Two-head tosses: " + heads);
		System.out.println("Two-tail tosses: " + tails);
		System.out.println("One-head one-tail tosses: " + headTails);
		System.out.println("Tosses add up correctly? " + (trials == (tails + heads + headTails)));
		
		test.run(10);
		trials = test.getNumTrials();
		heads = test.getTwoHeads();
		tails = test.getTwoTails();
		headTails = test.getHeadTails();
		System.out.println("After run" + "(" + 10 + "):");
		System.out.println("Number of trials [exp:11]: " + trials);
		System.out.println("Two-head tosses: " + heads);
		System.out.println("Two-tail tosses: " + tails);
		System.out.println("One-head one-tail tosses: " + headTails);
		System.out.println("Tosses add up correctly? " + (trials == (tails + heads + headTails)));
		
		test.run(100);
		trials = test.getNumTrials();
		heads = test.getTwoHeads();
		tails = test.getTwoTails();
		headTails = test.getHeadTails();
		System.out.println("After run" + "(" + 100 + "):");
		System.out.println("Number of trials [exp:111]: " + trials);
		System.out.println("Two-head tosses: " + heads);
		System.out.println("Two-tail tosses: " + tails);
		System.out.println("One-head one-tail tosses: " + headTails);
		System.out.println("Tosses add up correctly? " + (trials == (tails + heads + headTails)));
		
		test.reset();
		trials = test.getNumTrials();
		heads = test.getTwoHeads();
		tails = test.getTwoTails();
		headTails = test.getHeadTails();
		System.out.println("After reset: ");
		System.out.println("Number of trials [exp:0]: " + trials);
		System.out.println("Two-head tosses: " + heads);
		System.out.println("Two-tail tosses: " + tails);
		System.out.println("One-head one-tail tosses: " + headTails);
		System.out.println("Tosses add up correctly? " + (trials == (tails + heads + headTails)));
		
		test.run(1000);
		trials = test.getNumTrials();
		heads = test.getTwoHeads();
		tails = test.getTwoTails();
		headTails = test.getHeadTails();
		System.out.println("After run" + "(" + 1000 + "):");
		System.out.println("Number of trials [exp:1000]: " + trials);
		System.out.println("Two-head tosses: " + heads);
		System.out.println("Two-tail tosses: " + tails);
		System.out.println("One-head one-tail tosses: " + headTails);
		System.out.println("Tosses add up correctly? " + (trials == (tails + heads + headTails)));
		
	}
}
