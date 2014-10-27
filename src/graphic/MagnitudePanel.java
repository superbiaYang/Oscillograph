/**
 * The panel to display the color of the different magnitude level
 */
package graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class MagnitudePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6273226144372860098L;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		
		int skip = 10;
		int height = this.getHeight() / 8;
		
		g.setColor(Color.BLUE);
		g.fillRect(skip, this.getHeight()-skip-height, this.getWidth()-2*skip, height);
		
		g.setColor(Color.GREEN);
		g.fillRect(skip, this.getHeight()-skip*2-height*2, this.getWidth()-2*skip, height);
		
		g.setColor(Color.YELLOW);
		g.fillRect(skip, this.getHeight()-skip*3-height*3, this.getWidth()-2*skip, height);
		
		g.setColor(Color.RED);
		g.fillRect(skip, this.getHeight()-skip*4-height*4, this.getWidth()-2*skip, height);
	}
}
