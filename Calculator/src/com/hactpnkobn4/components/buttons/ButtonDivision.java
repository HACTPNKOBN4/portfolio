package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.panels.Display;

public class ButtonDivision extends JButton implements Operation {

	public static final Operation DIVISION = new ButtonDivision();

	public ButtonDivision() {
		setText("/");
		addActionListener(new Action());
	}
	
	class Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
	}
	
	public static void buttonPressed() {
		if(Calculator.currentOperation!=Calculator.NULL_OPERATION&&!Display.MustRefresh) {
			Calculator.currentOperation.doOperation();
			Display.backNumber=new StringBuilder(Display.NULL);
		}
		Calculator.currentOperation = DIVISION;
		Display.MustRefresh=true;
	}

	public void doOperation() {
		double n1=Display.getFront();
		double n2=Display.getBack();
		if(Display.MustRefresh) {
			Display.setFront(n1/n2);
		}
		else {
			Display.setFront(n2/n1);
			Display.setBack(n1);
		}
		Display.refresh();
		Display.MustRefresh=true;
	}
}
