package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;

public class ButtonEqual extends JButton {

	public ButtonEqual() {
		setText("=");
		addActionListener(new Action());
	}

	class Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
	}

	public static void buttonPressed() {
		if(Calculator.currentOperation!=Calculator.NULL_OPERATION) {
			Calculator.currentOperation.doOperation();
		}
	}	
}
