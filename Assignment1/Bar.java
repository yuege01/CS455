// Name:Ziwei Fang
// USC NetID:ziweifan
// CS 455 PA1
// Fall 2018

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
	private int barBottom;
	private int barLeft;
	private int barWidth;
	private int barHeight;
	private double barScale;
	private Color barColor;
	private String barLabel;
	
   


   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {
				  barBottom = bottom;
				  barLeft = left;
				  barWidth = width;
				  this.barHeight = barHeight;
				  barScale = scale;
				  barColor = color;
				  barLabel = label;
   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
public void draw(Graphics2D g2) {
      int height = (int)(barHeight * barScale);
      Font font = g2.getFont();
      FontRenderContext context = g2.getFontRenderContext();
      Rectangle2D labelBounds = font.getStringBounds(barLabel, context);
      int widthOfLabel = (int)labelBounds.getWidth();
      int heightOfLabel = (int)labelBounds.getHeight();
   
   // draw the graphics in appropriate place under the bar
      g2.drawString(barLabel, barLeft + barWidth / 2 - widthOfLabel / 2, barBottom); 
   
   // deal with the situation where bar height is 0 
      int w = barWidth;
      if(height == 0) {
		   w = 0;
	    }
      Rectangle body = new Rectangle(barLeft, barBottom - height - heightOfLabel, w, height); 
      g2.setColor(barColor);
      g2.fill(body);
      g2.draw(body);
   // set g2 color to black to ensure next graphics context is black
      g2.setColor(Color.BLACK);
   }
}
