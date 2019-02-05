package com.hactpnkobn4.components.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.hactpnkobn4.components.buttons.*;

public class OperationsPanel extends JPanel {
	
	public OperationsPanel() {
		setLayout(new GridLayout(5,2));
		add(new ButtonBackspace());
		add(new ButtonClear());
		add(new ButtonAddition());
		add(new ButtonPower());
		add(new ButtonSubtraction());
		add(new ButtonSquare());
		add(new ButtonMultiplication());
		add(new ButtonPercent());
		add(new ButtonDivision());
		add(new ButtonEqual());
	}

}
