package gui;

import importdata.Reading;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// This class creates a main matrix field for the crossword
public class Field {

	// Creation of Reading object to access methods from the class
	Reading file = new Reading();

	// Creation of JButton 2d array where we will put out button
	// Static because we want to access this field with methods in this class and
	// others
	protected static JButton[][] button;
	JPanel panel = new JPanel();

	// Method that sets text put from keyboard to the field
	protected static void setButton(int i, int j, String text) {
		button[i][j].setText(text);
	}

	// Method for the field creation
	// We access our imported 2d array with strings and basing on values create
	// different buttons
	private JPanel getField() {
		button = new JButton[file.getRows()][file.getCols()];
		panel.setLayout(new GridLayout(file.getRows(), file.getCols()));

		for (int i = 0; i < file.getCols(); i++) {
			for (int j = 0; j < file.getRows(); j++) {
				if (file.readField()[i][j].equals("O")) {
					button[i][j] = new JButton();
					int ii = i;
					int jj = j;
					button[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							new Keyboard(ii, jj).keyboardField(); // use of standard keyboard constructor
						}
					});
					button[i][j].setFont(new Font("Arial", Font.PLAIN, 18));
					button[i][j].setFocusPainted(false);
					button[i][j].setContentAreaFilled(true);
					button[i][j].setBackground(Color.WHITE);
					button[i][j].setOpaque(true);
					panel.add(button[i][j]);
				} else if (file.readField()[i][j].equals("S")) {
					button[i][j] = new JButton();
					int ii = i;
					int jj = j;
					button[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							new Keyboard(ii, jj).keyboardField(); // use of standard keyboard constructor
						}
					});
					button[i][j].setFont(new Font("Arial", Font.PLAIN, 18));
					button[i][j].setFocusPainted(false);
					button[i][j].setContentAreaFilled(true);
					button[i][j].setBackground(Color.GRAY);
					button[i][j].setOpaque(true);
					panel.add(button[i][j]);
				} else if (file.readField()[i][j].equals("X")) {
					button[i][j] = new JButton();
					button[i][j].setFocusPainted(false);
					button[i][j].setEnabled(false);
					button[i][j].setContentAreaFilled(true);
					button[i][j].setBackground(Color.BLACK);
					button[i][j].setOpaque(true);
					panel.add(button[i][j]);
				} else {
					button[i][j] = new JButton();
					int ii = i;
					int jj = j;
					String clue = file.readField()[i][j];
					String letterClue = String.valueOf(clue.charAt(2));
					button[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							new Keyboard(ii, jj, letterClue).keyboardFieldClue(); // using other keyboard constructor
																					// with a clue
						}
					});
					button[i][j].setFont(new Font("Arial", Font.PLAIN, 18));
					button[i][j].setFocusPainted(false);
					button[i][j].setContentAreaFilled(true);
					button[i][j].setBackground(new Color(173, 241, 255));
					button[i][j].setOpaque(true);
					panel.add(button[i][j]);
				}
			}
		}
		panel.setPreferredSize(new Dimension(725, 725));
		return panel;
	}

	// Method to export resulting field panel to visualization
	protected JPanel returnPanel() {
		Field panelField = new Field();
		return panelField.getField();
	}
}