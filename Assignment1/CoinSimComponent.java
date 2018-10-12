import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
/**
*  CoinSimComponent class
* 
*  This component draws three bars showing the results of two coin
*  tosses results indicated by bar height and percentage for trial result
*/
public class CoinSimComponent extends JComponent {  
   int trials;
   int twoHead;
   int twoTails;
   int headTails;
   
   public CoinSimComponent(int trials, int twoHead, int twoTails, int headTails) {
       this.trials = trials;
	   this.twoHead = twoHead;
       this.twoTails = twoTails;
       this.headTails = headTails;
   }
   
   public void paintComponent(Graphics g)
   {  
	  Graphics2D g2 = (Graphics2D) g;
	  Font font = g2.getFont();
      
      // get the size of the current component and of the graphics context
	  FontRenderContext context = g2.getFontRenderContext();
	  Rectangle2D labelBounds = font.getStringBounds("", context);
      int widthOfLabel = (int)labelBounds.getWidth();
      int heightOfLabel = (int)labelBounds.getHeight();
      int x = getWidth();
	  int y = getHeight();
      
      // Define some important constants calculated by the dimension above
      final int BAR_INTERVAL = x / 4;
	  final int BAR_WIDTH = 50;
	  final int FRAME = y / 20;
	  final int BOTTOM = y - FRAME;
	  final int TALLEST = y - 2 * FRAME - heightOfLabel;
	  final double SCALE = TALLEST / 100.0;
      
      // calculate the percentage of each result and make strings for construction of context
	  double percentTwoHead = (twoHead / (double) trials * 100);
	  double percentTwoTails = (twoTails / (double) trials * 100);
	  double percentHeadTails = 100 - (int)percentTwoTails - (int)percentTwoHead;
	  String sHead = "Two Heads: " + twoHead + "(" + (int)percentTwoHead + "%)";
	  String sTail = "Two Tails: " + headTails + "(" + (int)percentTwoTails + "%)";
	  String sHeadTails = "A Head and a Tail: " + twoTails + "(" + (int)percentHeadTails + "%)";
      
      Bar car1 = new Bar(BOTTOM, BAR_INTERVAL - BAR_WIDTH / 2, BAR_WIDTH, (int)percentTwoHead, SCALE, Color.RED, sHead);
 	  Bar car2 =  new Bar(BOTTOM, 2 * BAR_INTERVAL - BAR_WIDTH / 2, BAR_WIDTH,(int)percentHeadTails , SCALE, Color.GREEN,       sHeadTails);
	  Bar car3 =  new Bar(BOTTOM, 3 * BAR_INTERVAL - BAR_WIDTH / 2, BAR_WIDTH, (int)percentTwoTails , SCALE, Color.BLUE, sTail);

      car1.draw(g2);  
	  car2.draw(g2);
	  car3.draw(g2);
   }
}
