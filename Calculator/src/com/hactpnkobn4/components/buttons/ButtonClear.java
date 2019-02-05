package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.panels.Display;

public class ButtonClear extends JButton {

	public ButtonClear() {
		setText("C");
		addActionListener(new Action());
	}
	
	class Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
	}
	
	public static void buttonPressed() {
		Display.clearFront();
		Display.clearBack();
		Calculator.currentOperation=Calculator.NULL_OPERATION;
		Calculator.display.refresh();
	}
}
