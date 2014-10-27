/**
 * This is a checkbox for switch test start/finish
 */
package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import data.MagnitudeTester;

/**
 * @author yhmShanghai@gmail.com
 *
 */
public class TestingSwitch extends JCheckBox implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8114253143795969729L;
	private MagnitudeTester mTester;
	/**
	 * A switch must relate to a tester
	 */
	public TestingSwitch(MagnitudeTester tester, String name) {
		super(name);
		mTester = tester;
		this.addItemListener(this);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (this.isSelected()) {
			mTester.testStart();
		} else {
			mTester.testEnd();
		}
	}

}
