package controller;

import gnu.io.CommPortIdentifier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.DataCollector;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class PortChooser implements ActionListener {
	private DataCollector mCollector;
	private CommPortIdentifier mPort;
	
	public void setCollector(DataCollector collector) {
		this.mCollector = collector;
	}
	
	public void setPort(CommPortIdentifier port) {
		this.mPort = port;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(mCollector != null) {
			mCollector.stopCollect();
			mCollector.setListenPort(this.mPort);
		}
	}

}
