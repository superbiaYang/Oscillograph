package controller;

import graphic.GraphicGenerator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class GraphicXContorller implements MouseListener {
	public static int UP = 0;
	public static int DOWN = 1;
	private GraphicGenerator mGraphicGenerator;
	private int mType;
	public GraphicXContorller( GraphicGenerator gg, int type ) {
		mGraphicGenerator = gg;
		mType = type;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (mType == UP) {
			mGraphicGenerator.XUp();
		} else if (mType == DOWN) {
			mGraphicGenerator.XDown();
		} 
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
