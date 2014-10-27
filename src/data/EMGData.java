package data;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class EMGData {
	private int mVoltage;
	private long mTimestamp;
	
	/**
	 * @param voltage
	 * The voltage get in real time
	 * This constructor will use current time as the timestamp
	 */
	public EMGData( int voltage ) {
		mTimestamp = System.currentTimeMillis();
		mVoltage = voltage;
	}
	
	/**
	 * @param voltage
	 * @param timestamp
	 * This constructor is used for history data
	 */
	public EMGData( int voltage, long timestamp ) {
		mTimestamp = timestamp;
		mVoltage = voltage;
	}
	
	/**
	 * @param maxvoltage
	 * @param minvoltage
	 * @param height
	 * @return
	 * Use maxvoltage as the max Y of the graphic.
	 * Use minvoltage as the min Y of the graphic.
	 * Calculate the Y of the graphic height
	 */
	public int getY( int maxvoltage, int minvoltage, int height ) {
		if (maxvoltage == minvoltage) {
			return 0;
		}
		if ( mVoltage < minvoltage ) {
			return height;
		}
		if ( mVoltage > maxvoltage ) {
			return 0;
		}
		return height - ( mVoltage - minvoltage ) * height / ( maxvoltage - minvoltage );
	}
	
	/**
	 * @param starttimestamp
	 * @param spacetime
	 * @param spacex
	 * @return
	 * Calculate the X (timestamp - starttimestamp) * spacex / spacetime
	 */
	public int getX( long starttimestamp, int spacetime, int spacex ) {
		return (int) (mTimestamp - starttimestamp) * spacex / spacetime;
	}
	
	public int getVoltage() {
		return mVoltage;
	}
	public long getTimestamp() {
		return mTimestamp;
	}
}
