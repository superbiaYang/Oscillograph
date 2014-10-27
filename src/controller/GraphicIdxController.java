package controller;

import graphic.GraphicGenerator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class GraphicIdxController implements MouseListener {
	enum TYPE {
		LEFT,
		RIGHT
	}
	private TYPE mType;
	private GraphicGenerator mGraphic;
	public GraphicIdxController( GraphicGenerator g, TYPE type ) {
		mGraphic = g;
		mType = type;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if( mGraphic == null ) {
			return;
		}
		if (mType == TYPE.LEFT) {
			mGraphic.setStartIdx( mGraphic.getStartIdx() + 1);
		} else if (mType == TYPE.RIGHT) {
			mGraphic.setStartIdx( mGraphic.getStartIdx() - 1);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
