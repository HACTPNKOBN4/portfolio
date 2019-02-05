package com.hactpnkobn4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.*;

import com.hactpnkobn4.components.*;
import com.hactpnkobn4.components.buttons.NullOperation;
import com.hactpnkobn4.components.buttons.Operation;
import com.hactpnkobn4.components.panels.Display;
import com.hactpnkobn4.components.panels.KeyboardPanel;
import com.hactpnkobn4.components.panels.OperationsPanel;

public class Calculator extends JFrame{
	
	/*Константы состояния. В зависимости от значения поля actionState 
	 *будет меняться действие при нажатии кнопки равно или кнопок цифр.*/

	
	
	public final static Operation NULL_OPERATION = new NullOperation();
	public static Operation currentOperation;
	public static Display display;
	
	
	public Calculator() {
		currentOperation = NULL_OPERATION;
		InitializeWindow();
	}
	
	public static void main(String args[]) {
		new Calculator().setVisible(true);
	}
	
	
	private void InitializeWindow() {
		setTitle("Калькулятор");
		setSize(600,200);
		setDefaultCloseOperation(3);
		setLayout(new BorderLayout());
		setResizable(false);
		
		display = new Display();
		add(display,BorderLayout.NORTH);
		display.activate();
		display.setFont(new Font(Font.DIALOG,Font.PLAIN,30));
		
		add(new KeyboardPanel(), BorderLayout.LINE_START);
		add(new OperationsPanel(),BorderLayout.LINE_END);
		
		pack();
		setLocationRelativeTo(null);
	}
}
