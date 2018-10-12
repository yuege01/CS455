import java.util.Scanner;
import javax.swing.JFrame;

/**
 * class CoinSimViewer
 * This the class containing main() and starts the simulation and 
 * construct graphics, to start trails, user need to enter a valid number
 * (the number must be larger than 0), or program will prompt the user to
 * re-enter a valid number for the trial
 */
public class CoinSimViewer {
	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
		System.out.print("Enter number of trials: ");
		int trials = in.nextInt();
		while(trials <= 0) {
			System.out.println("Error: Number Entered must be greater than 0.");
			System.out.print("Enter number of trials: ");
			trials = in.nextInt();
		}
	    JFrame frame = new JFrame();
		frame.setSize(800, 500);
        frame.setTitle("CoinSum");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // Simulate the trial and send the results to CoinSimComponent
		CoinTossSimulator simulator = new CoinTossSimulator();
	    simulator.run(trials);
	    int twoHead = simulator.getTwoHeads();
	    int twoTails = simulator.getTwoTails();
	    int headTails = simulator.getHeadTails();
        CoinSimComponent component = new CoinSimComponent(trials, twoHead, twoTails, headTails);
        frame.add(component);
		
        frame.setVisible(true);
	}
}
