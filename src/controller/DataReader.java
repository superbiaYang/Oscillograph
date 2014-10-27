package controller;

import graphic.GraphicGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class DataReader implements ActionListener {
	private GraphicGenerator mGraphic;
	public DataReader( GraphicGenerator g ) {
		mGraphic = g;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter ff = new FileNameExtensionFilter( "emg file", "emg" );
		fc.setFileFilter(ff);
		int option = fc.showOpenDialog(null);
		if(option == JFileChooser.APPROVE_OPTION){
			String path = fc.getSelectedFile().getAbsolutePath();
			mGraphic.openHistory(path);
		}
	}

}
