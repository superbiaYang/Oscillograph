package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.DataCollector;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class DataSaver implements ActionListener {
	private DataCollector mDataCollector;
	DataSaver(DataCollector dc) {
		mDataCollector = dc;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter ff = new FileNameExtensionFilter( "emg file", "emg" );
		fc.setFileFilter(ff);
		int option = fc.showSaveDialog(null);
		if(option == JFileChooser.APPROVE_OPTION){
			String path = fc.getSelectedFile().getAbsolutePath();
			if(!path.endsWith(".emg")){
	        	path=path+".emg";
	        }
			mDataCollector.saveRecords(path);
		}
	}

}
