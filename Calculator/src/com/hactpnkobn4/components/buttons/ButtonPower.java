package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.buttons.ButtonAddition.Action;
import com.hactpnkobn4.components.panels.Display;

public class ButtonPower extends JButton {

	public ButtonPower() {
		setText("pow2");
		addActionListener(new Action());
	}
	
	class Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
	}
	
	public static void buttonPressed() {
		if (Display.backNumber.toString().equals(Display.NULL)	||	Display.MustRefresh) {
			double n=Display.getFront();
			Display.setFront(Math.pow(n, 2d));
			Display.clearBack();
			Display.MustRefresh=true;
			Display.refresh();
			Calculator.currentOperation=Calculator.NULL_OPERATION;
		}
	}
}
