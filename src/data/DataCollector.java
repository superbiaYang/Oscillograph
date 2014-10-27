package data;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import graphic.GraphicGenerator;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author yhmShanghai@gmail.com
 */
public class DataCollector extends MagnitudeTester implements Runnable {
	private CommPortIdentifier  mPort;
	private GraphicGenerator mGraphic;
	private Boolean mFlag;
	
	private List<EMGData> mCollectList = Collections.synchronizedList( new LinkedList<EMGData>() );
	private List<EMGData> mRecords = new ArrayList<EMGData>();
	private boolean mIsRecord = false;

	public void setGraphic( GraphicGenerator graphic ) {
		mGraphic = graphic;
	}
	
	public void setRecord( boolean record ) {
		mIsRecord = record;
		if(mIsRecord){
			mRecords.clear();
		}
	}
	
	public void stopCollect() {
		mFlag = false;
	}
	
	public List<EMGData> getCollectingList() {
		return mCollectList;
	}
	
	public void addData( int newdata ) {
		EMGData data = new EMGData(newdata);
		mCollectList.add(data);
		if(mIsRecord) {
			mRecords.add(data);
		}
		if (mInTest) {
			this.newTestData(newdata);
		}
	}
	
	public void setListenPort( CommPortIdentifier port ) {
		mPort = port;
		mGraphic.setStatus( GraphicGenerator.REALTIME );
	}
	
	public void saveRecords( String location ) {
		File f = new File(location);
        DataOutputStream stream;
        try {
        	FileOutputStream fs = new FileOutputStream(f);
        	stream = new DataOutputStream( fs );
            int size = mRecords.size();
            for (int i = 0; i < size; i++) {
            	stream.writeLong(mRecords.get(i).getTimestamp());
            	stream.writeInt(mRecords.get(i).getVoltage());
			}
            stream.flush();
            stream.close();
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void run() {
		while( true )
		{
			try {
				Thread.sleep(1);
				//Thread.sleep( skipTime );
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
			if ( mPort == null ) {
				continue;
			}
			SerialPort workingport = null;
			try {
				workingport = (SerialPort)mPort.open("EMG", 1000);
			} catch (PortInUseException e2) {
				e2.printStackTrace();
			}
			InputStream is = null;
			try {
				is = new BufferedInputStream(workingport.getInputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			mFlag = true;
			mCollectList.clear();
			while( mFlag ) {
				Random rand = new Random();
				try {
					int data = is.read();
					data = rand.nextInt(5000);
					addData(data);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					//Thread.sleep( skipTime );
					Thread.sleep(1);
				} catch ( InterruptedException e ) {
					continue;
			    }
			}
			workingport.close();
		}
	}
}
