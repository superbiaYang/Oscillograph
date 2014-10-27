package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import data.DataCollector;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class TimeController implements MouseListener{
	public static int UP = 0;
	public static int DOWN = 1;
	private DataCollector mCollector;
	private int mType;
	
	public TimeController(DataCollector collector, int type){
		mCollector = collector;
		mType = type;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mCollector==null){
			return;
		}
		if(mType==UP){
			//mCollector.setTime(mCollector.getTime()+100); 
		} else if(mType==DOWN){
			//mCollector.setTime(mCollector.getTime()-100);
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
