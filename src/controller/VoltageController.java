package controller;

import graphic.GraphicGenerator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class VoltageController implements MouseListener{
	public static int UP = 0;
	public static int DOWN = 1;
	GraphicGenerator mGraphic;
	int mType;
	public VoltageController(GraphicGenerator graphic, int type) {
		mGraphic = graphic;
		mType = type;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mGraphic==null){
			return;
		}
		if(mType==UP){
			mGraphic.YUp();
		} else if(mType==DOWN){
			mGraphic.YDown();
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
