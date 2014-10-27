package graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import data.DataCollector;
import data.EMGData;

/**
 * @author yhmShanghai@gmail.com
 */
public class GraphicGenerator extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -922126309927076903L;
	
	public static int HISTORY = 0;
	public static int REALTIME = 1;
	
	/**
	 * Use to flag a data as the standard of others
	 */
	private int mFlagIdx = 0;
	private int mStatus = HISTORY;
	
	private List<Integer> datalist = Collections.synchronizedList( new LinkedList<Integer>() );
	private List<EMGData> mHistory;
	private int mStartIdx = 0;
	private int mVoltage = 200;
	private DataCollector mDataCollector;
	
	public GraphicGenerator(DataCollector dataCollector) {
		mDataCollector = dataCollector;
		mDataCollector.setGraphic(this);
	}

	public void setVoltage( int voltage ) {
		this.mVoltage = voltage;
		if(mVoltage <= 0 ){
			mVoltage = 1;
		}
		datalist.clear();
	}
	
	public void setTime( int time ) {
	}
	
	public void setStartIdx( int startIdx ){
		this.mStartIdx = startIdx;
		if(this.mStartIdx < 0){
			this.mStartIdx = 0;
		}
	}
	
	public int getStartIdx(){
		return this.mStartIdx;
	}
	
	public void setStatus( int status ) {
		mStatus = status;
	}
	
	public int getVoltage() {
		return mVoltage;
	}
	
	@Override
	public void paint( Graphics g ) {
		super.paint(g);
		this.setBackground( Color.BLACK );
		g.setColor( Color.WHITE );
		if (mStatus == REALTIME) {
			paintRealTime(g);
		} else if(mStatus == HISTORY){
			paintHistory(g);
		}
	}
	
	private int mSpaceTime = 1;
	private int mSpaceX = 1;
	
	private void paintRealTime( Graphics g ) {
		List<EMGData> paintList = mDataCollector.getCollectingList();
		if ( paintList == null || paintList.size() <= 1 ) {
			return;
		}
		
		if ( paintList.size() <= mStartIdx ) {
			mStartIdx = 1;
		}
		
		mFlagIdx = paintList.size() - 1;

		Color flagColor = mDataCollector.getGraphicColor(paintList.get(mFlagIdx));
		Color startColor = mDataCollector.getGraphicColor(paintList.get(mStartIdx));
		if (flagColor != startColor) {
			mStartIdx = mFlagIdx;
		}
		g.setColor( flagColor );
		
		long starttimestamp = paintList.get( mStartIdx ).getTimestamp();
		int spacetime = mSpaceTime;
		int spacex = mSpaceX;
		
		int maxvoltage = (int) (mDataCollector.getMaxVoltage(paintList.get(mFlagIdx)) * mYFix);
		int minvoltage = mDataCollector.getMinVoltage(paintList.get(mFlagIdx));
		int height = this.getHeight();
		
		int x1 = paintList.get(mStartIdx).getX(starttimestamp, spacetime, spacex);
		int y1 = paintList.get(mStartIdx).getY(maxvoltage, minvoltage, height);
		int x2;
		int y2;
		
		for (int i = mStartIdx + 1; i < paintList.size(); i++) {
			x2 = paintList.get( i ).getX(starttimestamp, spacetime, spacex);
			y2 = paintList.get( i ).getY(maxvoltage, minvoltage, height);
			g.drawLine(x1, y1, x2, y2);
			/*
			System.out.print(x1);
			System.out.print(",");
			System.out.print(y1);
			System.out.print(",");
			System.out.print(x2);
			System.out.print(",");
			System.out.println(y2);*/
			if (x2 > this.getWidth()) {
				mStartIdx++;
				break;
			}
			x1 = x2;
			y1 = y2;
		}
	}
	
	private void paintHistory( Graphics g ) {
		List<EMGData> paintList = mHistory;
		if ( paintList == null || paintList.size() == 0 ) {
			return;
		}
		
		/*
		mPrintList = datalist;
		if ( mPrintList == null || mPrintList.size() <= mStartIdx ) {
			return;
		}
		mFlagIdx = mPrintList.size() - 1;
		*/
		Color flagColor = mDataCollector.getGraphicColor(paintList.get(mStartIdx));
		g.setColor( flagColor );
		
		long starttimestamp = paintList.get( mStartIdx ).getTimestamp();
		int spacetime = mSpaceTime;
		int spacex = mSpaceX;
		
		int maxvoltage = (int) (mDataCollector.getMaxVoltage(paintList.get(mStartIdx)) * mYFix);
		int minvoltage = mDataCollector.getMinVoltage(paintList.get(mStartIdx));
		int height = this.getHeight();
		System.out.print(maxvoltage);
		System.out.print(",");
		System.out.println(minvoltage);;
		int x1 = paintList.get(mStartIdx).getX(starttimestamp, spacetime, spacex);
		int y1 = paintList.get(mStartIdx).getY(maxvoltage, minvoltage, height);
		int x2;
		int y2;
		
		for (int i = mStartIdx + 1; i < paintList.size(); i++) {
			x2 = paintList.get( i ).getX(starttimestamp, spacetime, spacex);
			y2 = paintList.get( i ).getY(maxvoltage, minvoltage, height);
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		
	}

	public void openHistory(String path) {
		File f = new File(path);
        DataInputStream stream = null;
        FileInputStream fs = null;
        mHistory = new ArrayList<EMGData>();
        try {
        	fs = new FileInputStream( f );
        	stream = new DataInputStream(fs);
        	while( stream.available() != -1 ) {
        		long timestamp = stream.readLong();
        		int data=stream.readInt();
        		mHistory.add(new EMGData(data, timestamp));
        		System.out.println(data);
        	}
        	
        } catch( EOFException eofException ) {
        	//read file finish
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
        		if ( fs != null ) {
        			fs.close();					
				}
				if ( stream != null ) {
					stream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        setStatus(HISTORY);
        mStartIdx = 0;
	}

	public void run() {
		while(true){
			this.repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void XUp() {
		if (mSpaceTime > 1) {
			mSpaceTime--;
		} else {
			mSpaceX++;
		}
	}
	
	public void XDown() {
		if (mSpaceX > 1) {
			mSpaceX--;
		} else {
			mSpaceTime++;
		}
	}
	
	private double mYFix = 1;
	public void YUp() {
		mYFix = mYFix - 0.1;
		if (mYFix < 0.1) {
			mYFix = 0.1;
		}
	}
	public void YDown() {
		mYFix = mYFix + 0.1;
	}
}
