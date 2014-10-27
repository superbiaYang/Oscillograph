/**
 * The logic of testing the magnitude
 */
package data;

import java.awt.Color;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class MagnitudeTester {
	private int mMax = -1;
	private int mMin = -1;
	protected boolean mInTest = false;
	
	public void testStart() {
		this.reset();
		mInTest = true;
	}
	
	public void testEnd() {
		mInTest = false;
	}
	
	/**
	 * Compare the new data with max and min data and refresh them
	 */
	protected void newTestData( int newData ) {
		if( newData>mMax || mMax==-1 ) {
			mMax = newData;
		}
		if (newData<mMin || mMin==-1) {
			mMin = newData;
		}
	}
	
	public void reset() {
		mMax = -1;
		mMin = -1;
	}
	public int getMaxVoltage( EMGData emgData ) {
		if (mMax==-1||mInTest) {
			return 5000;
		}
		int gap = (mMax-mMin)/4;
		return (emgData.getVoltage()/gap+1)*gap;
	}
	public int getMinVoltage( EMGData emgData ) {
		if (mMax==-1||mInTest) {
			return 0;
		}
		int gap = (mMax-mMin)/4;
		return (emgData.getVoltage()/gap)*gap;
	}
	public Color getGraphicColor( EMGData emgData ) {
		if (mMax==-1||mInTest) {
			return Color.WHITE;
		} else {
			int gap = (mMax-mMin)/4;
			int choice = emgData.getVoltage()/gap;
			switch (choice) {
			case 0:
				return Color.BLUE;
			case 1:
				return Color.GREEN;
			case 2:
				return Color.YELLOW;
			default:
				break;
			}
		}
		return Color.RED;
	}
}
