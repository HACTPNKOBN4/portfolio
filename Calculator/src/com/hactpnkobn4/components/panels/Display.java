package com.hactpnkobn4.components.panels;

import javax.swing.JLabel;

import com.hactpnkobn4.Calculator;

public class Display extends JLabel {

	public static final int MAX_NUMBER_LENGTH = 10;
	public static final String NULL = "NULL";

	public static StringBuilder frontNumber, backNumber;
	public static boolean MustRefresh=false;

	public void activate() {
		frontNumber = new StringBuilder().append("0");
		backNumber = new StringBuilder().append(NULL);
		setSize(200, 40);
		setFront(0);
		refresh();
	}



	public static void clearFront() {
		frontNumber = new StringBuilder("0");
	}

	public static void clearBack() {
		backNumber = new StringBuilder(NULL);
	}

	public static void setBackToFront() {
		backNumber=new StringBuilder().append(frontNumber);
	}

	public static double getFront() {
		return Double.parseDouble(frontNumber.toString());
	}
	
	public static void setFront(double n) {
		frontNumber = new StringBuilder().append(n);
		if(frontNumber.length()>2 && frontNumber.substring(frontNumber.length()-2).equals(".0")) {
			frontNumber.delete(frontNumber.length()-2, frontNumber.length());
		}
	}

	public static double getBack() {
		return Double.parseDouble(backNumber.toString());
	}
	
	public static void setBack(double n) {
		backNumber = new StringBuilder().append(n);
	}

	public static void refresh() {
		if (frontNumber.toString().equals(NULL)) Calculator.display.setText("0");
		else Calculator.display.setText(frontNumber.toString());
	}
}
