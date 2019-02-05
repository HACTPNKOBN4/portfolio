package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.panels.Display;

public class ButtonComma extends JButton {

	public ButtonComma() {
		setText(".");
		addActionListener(new Action());
	}
	
	class Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
	}
	
	public static void buttonPressed() {
		if(Display.frontNumber.toString().matches(".+\\..+?"))System.out.println("TOCHKA STOIT!");
		else Display.frontNumber.append(".");
		Display.refresh();
	}
}
