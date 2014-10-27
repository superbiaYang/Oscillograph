package controller;

import java.awt.Container;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import data.DataCollector;
import graphic.GraphicGenerator;
import graphic.MagnitudePanel;

/**
 * @author yhmShanghai@gmail.com
 */
public class Oscillorgraph {
	private static JFrame mainFrame;
	private static DataCollector dataCollector;
	private static GraphicGenerator graphicGenerator;
	
	private static void setMenu() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");
		JMenuItem fileOpen = new JMenuItem("open");
		fileOpen.addActionListener(new DataReader(graphicGenerator));
		JMenuItem fileSave = new JMenuItem("save");
		fileSave.addActionListener(new DataSaver(dataCollector));
		menuFile.add(fileOpen);
		menuFile.add(fileSave);
		
		JMenu menuTool = new JMenu("Tool");
		JMenu toolPorts = new JMenu("ports");
		
		
		PortSelector portSelector = new PortSelector();
		portSelector.setCollector(dataCollector);
	    portSelector.setMenu(toolPorts);
		toolPorts.addMouseListener(portSelector);
		menuTool.add(toolPorts);
				
		menubar.add(menuFile);
		menubar.add(menuTool);
		mainFrame.setJMenuBar( menubar );
	}
	
	public static void setGraphicPanel() {
		Container cont = mainFrame.getContentPane();
		graphicGenerator.setLayout(null);
		int framewidth = mainFrame.getWidth();
		int frameheight = mainFrame.getHeight();
		graphicGenerator.setBounds(60,30,framewidth-210,frameheight-150);
		cont.add(graphicGenerator);
		JLabel signalLabel = new JLabel("Signal:");
		signalLabel.setBounds(50, 5, 100, 10);
		cont.add(signalLabel);
		
		JLabel voltageLabel = new JLabel("Voltage:");
		voltageLabel.setBounds(5, 100, 100, 10);
		cont.add(voltageLabel);
		
		JButton voltageUp = new JButton("+");
		voltageUp.setBounds(10, 130, 45, 45);
		voltageUp.addMouseListener( new VoltageController(graphicGenerator,VoltageController.UP));
		cont.add(voltageUp);
		
		JButton voltageDown = new JButton("-");
		voltageDown.setBounds(10, 180, 45, 45);
		voltageDown.addMouseListener( new VoltageController(graphicGenerator,VoltageController.DOWN));
		cont.add(voltageDown);
		
		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setBounds(100, frameheight-100, 100, 10);
		cont.add(timeLabel);
		
		JButton timeUp = new JButton("+");
		timeUp.setBounds(140, frameheight-110, 45, 45);
		timeUp.addMouseListener(new TimeController(dataCollector, TimeController.UP));
		timeUp.addMouseListener(new GraphicXContorller(graphicGenerator, GraphicXContorller.UP));
		cont.add(timeUp);
		
		JButton timeDown = new JButton("-");
		timeDown.setBounds(190, frameheight-110, 45, 45);
		timeDown.addMouseListener(new TimeController(dataCollector, TimeController.DOWN));
		timeDown.addMouseListener(new GraphicXContorller(graphicGenerator, GraphicXContorller.DOWN));
		cont.add(timeDown);
		
		JButton timeLeft = new JButton("<");
		timeLeft.setBounds(graphicGenerator.getWidth()/2-50, graphicGenerator.getHeight()-50, 45, 45);
		timeLeft.addMouseListener(new GraphicIdxController(graphicGenerator, GraphicIdxController.TYPE.LEFT));
		graphicGenerator.add(timeLeft);
		
		JButton timeRight = new JButton(">");
		timeRight.setBounds(graphicGenerator.getWidth()/2+50, graphicGenerator.getHeight()-50, 45, 45);
		timeRight.addMouseListener(new GraphicIdxController(graphicGenerator, GraphicIdxController.TYPE.RIGHT));
		graphicGenerator.add(timeRight);
		
		JCheckBox testing = new TestingSwitch(dataCollector, "testing");
		testing.setBounds(framewidth-120, frameheight-240, 100, 20);
		cont.add(testing);
		
		JCheckBox record = new JCheckBox("record");
		record.setBounds(framewidth-120, frameheight-200, 100, 20);
		record.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				dataCollector.setRecord(record.isSelected());
			}
			
		});
		cont.add(record);
		
		MagnitudePanel magnitudePanel = new MagnitudePanel();
		magnitudePanel.setBounds(framewidth-130, 50, 100, frameheight-300);
		cont.add(magnitudePanel);
	}
	
	public static void main(String[] args) {
		dataCollector = new DataCollector();
		graphicGenerator = new GraphicGenerator(dataCollector);
		
		mainFrame = new JFrame("EMG");
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setSize(800, 600);
		setMenu();
		setGraphicPanel();
		
		mainFrame.setVisible(true);
		
		new Thread(dataCollector).start();
		new Thread( graphicGenerator ).start();
		
		mainFrame.addComponentListener( new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				mainFrame.getContentPane().removeAll();
				graphicGenerator.removeAll();
				setGraphicPanel();			
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		mainFrame.addWindowListener( new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
