package com.hactpnkobn4.components.buttons;

import static com.hactpnkobn4.components.panels.Display.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.panels.Display;

public class ButtonNumber extends JButton {
	final int number;

	public ButtonNumber(int number) {
		setText(""+number);
		this.number = number;
		setSize(300,200);
		addActionListener(new Action());
	}
	
	class Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			action(number);
		}
	}
	
	public static void action(int number) {
		if(Display.MustRefresh) {
			setBackToFront();
			frontNumber = new StringBuilder().append(number);
			Display.MustRefresh=false;
		} else if (frontNumber.toString().equals("0")) {
			frontNumber = new StringBuilder().append(number);
			} else  if (frontNumber.length() != MAX_NUMBER_LENGTH) 
				frontNumber.append(number);
		
		refresh();
	}
}
