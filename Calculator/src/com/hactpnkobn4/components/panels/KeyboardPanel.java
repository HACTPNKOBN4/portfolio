package com.hactpnkobn4.components.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.hactpnkobn4.components.buttons.ButtonChangeSign;
import com.hactpnkobn4.components.buttons.ButtonComma;
import com.hactpnkobn4.components.buttons.ButtonNumber;

public class KeyboardPanel extends JPanel{

	public KeyboardPanel() {
		this.setLayout(new GridLayout(4,3));
		add(new ButtonNumber(1));
		add(new ButtonNumber(2));
		add(new ButtonNumber(3));
		add(new ButtonNumber(4));
		add(new ButtonNumber(5));
		add(new ButtonNumber(6));
		add(new ButtonNumber(7));
		add(new ButtonNumber(8));
		add(new ButtonNumber(9));
		add(new ButtonComma());
		add(new ButtonNumber(0));
		add(new ButtonChangeSign());
	}
}
