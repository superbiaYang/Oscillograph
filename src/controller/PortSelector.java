package controller;

import gnu.io.CommPortIdentifier;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import data.DataCollector;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class PortSelector implements MouseListener {
	private JMenu mMenu;
	private DataCollector mCollector;
	public void setMenu(JMenu menu) {
		this.mMenu = menu;
	}
	public void setCollector( DataCollector collector ) {
		this.mCollector = collector;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(mMenu == null) {
			return;
		}
		mMenu.removeAll();
		Vector<CommPortIdentifier> ports = getPortList();
		for( int i = 0; i < ports.size(); i++ ) {
			JMenuItem port = new JMenuItem(ports.elementAt(i).getName());
			PortChooser chooser = new PortChooser();
			chooser.setPort(ports.elementAt(i));
			chooser.setCollector(mCollector);
			port.addActionListener(chooser);
			mMenu.add(port);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	private Vector<CommPortIdentifier> getPortList() {
		CommPortIdentifier portId;
        Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();
        Vector<CommPortIdentifier> ports = new Vector<CommPortIdentifier>();
        // iterate through the ports.
        while ( en.hasMoreElements( )) {
            portId = (CommPortIdentifier)en.nextElement();
            if ( portId.getPortType() == CommPortIdentifier.PORT_SERIAL ) {
            		ports.add( portId );
            }
        }
        return ports;
	}
}
