package com.hactpnkobn4.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hactpnkobn4.Calculator;
import com.hactpnkobn4.components.panels.Display;

public class ButtonBackspace extends JButton{

	public ButtonBackspace() {
		setText("<");
		addActionListener(new Action());
	}
	
	class Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			buttonPressed();
		}
	}
	
	public static void buttonPressed() {
		if(Display.MustRefresh==false) {
			if(Display.frontNumber.toString().length()==1) {
				Display.setFront(0);
			} else Display.frontNumber.deleteCharAt(Display.frontNumber.length()-1);
			Display.refresh();
		}
	}
}
