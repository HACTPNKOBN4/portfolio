package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.panels.Display;

public class ButtonPercent extends JButton implements Operation {
	
	public static final Operation PERCENT = new ButtonPercent();

	public ButtonPercent() {
		setText("%");
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
		Calculator.currentOperation = PERCENT;
		Display.MustRefresh=true;
	}

	public void doOperation() {
		double n1=Display.getFront();
		double n2=Display.getBack();
		if(!Display.MustRefresh) {
			Display.setFront((n2*100)/n1);
			Display.clearBack();
		}
		Display.refresh();
		Display.MustRefresh=true;
	}
}
