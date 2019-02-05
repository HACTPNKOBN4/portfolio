package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.panels.Display;

public class ButtonAddition extends JButton implements Operation {
	
	public static final Operation ADDITION = new ButtonAddition();

	public ButtonAddition() {
		setText("+");
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
		Calculator.currentOperation = ADDITION;
		Display.MustRefresh=true;
	}

	public void doOperation() {
		if(!Display.backNumber.toString().equals(Display.NULL)) {
			double n1=Display.getFront();
			double n2=Display.getBack();
			Display.setFront(n1+n2);
			if(Display.MustRefresh)Display.setBack(n2);
			else Display.setBack(n1);
			Display.refresh();
			Display.MustRefresh=true;
		}
	}
}
