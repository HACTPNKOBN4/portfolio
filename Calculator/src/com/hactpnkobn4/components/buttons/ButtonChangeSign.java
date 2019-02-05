package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.panels.Display;

public class ButtonChangeSign extends JButton {

	public ButtonChangeSign() {
		setText("±");
		addActionListener(new Action());
	}
	
	class Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
	}
	
	public static void buttonPressed() {
		if(Display.frontNumber.toString().matches("0|0.")==false&&Display.MustRefresh==false) {
			if(Display.frontNumber.toString().matches("\\-.*")) {
				Display.frontNumber.deleteCharAt(0);
			} else {
				Display.frontNumber=new StringBuilder().append("-"+Display.frontNumber);
			}
			Display.refresh();
		}
	}
}
