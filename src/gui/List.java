package gui;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Dimension;
import importdata.Reading;
import javax.swing.*;
import java.awt.*;

// The following class creates a panel with a crossword supplying text
public class List {

	// Creation of instance variables. We create here a Reading object to access
	// info from text file
	JPanel panel = new JPanel();
	Reading file = new Reading();
	JTextPane pane = new JTextPane();

	StyledDocument doc = pane.getStyledDocument();

	// Constructor that creates a panel with crossword text field
	private JPanel getListPanel() {

		// Array of strings to store all elements
		String[] elements = new String[file.readingLines().length];

		// Creation of styles for different part of text
		SimpleAttributeSet attributeSetOne = new SimpleAttributeSet();
		StyleConstants.setBold(attributeSetOne, true);
		StyleConstants.setFontSize(attributeSetOne, 12);
		StyleConstants.setForeground(attributeSetOne, new Color(102, 0, 153));
		StyleConstants.setFontFamily(attributeSetOne, "Helvetica");

		SimpleAttributeSet attributeSetTwo = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attributeSetTwo, "Helvetica");
		StyleConstants.setFontSize(attributeSetTwo, 12);

		SimpleAttributeSet attributeSetThree = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attributeSetThree, "Helvetica");
		StyleConstants.setFontSize(attributeSetThree, 12);
		StyleConstants.setBold(attributeSetThree, true);

		// Initiation of styles and adding to TextPane through series of loops
		for (int i = file.readingLines().length - 1; i >= 0; i--) {
			elements[i] = file.readingLines()[i];
			// Firstly set style for additional spacing for lines with ABOVE and DOWN
			if (elements[i].equals("ACROSS")) {
				try {
					doc.insertString(0, '\n' + elements[i], attributeSetOne);
				} catch (BadLocationException e) {
					System.out.println("Bad Location, getListPanel" + e.toString());
				}
			} else if (elements[i].equals("DOWN")) {
				try {
					doc.insertString(0, '\n' + elements[i], attributeSetOne);
					doc.insertString(0, '\n' + "", null);
				} catch (BadLocationException e) {
					System.out.println("Bad Location, getListPanel" + e.toString());
				}
				// Next, going through rows and setting specific styles
			} else {
				// Firstly, going through first 9 rows, by condition if second character is ","
				if (String.valueOf(elements[i].charAt(1)).equals(".")) {
					// Catching tips, rows with years, where "," is the 7th character
					if (String.valueOf(elements[i].charAt(7)).equals(",")) {
						try {
							doc.insertString(0, '\n' + elements[i].substring(0, 2), attributeSetOne);
							doc.insertString(3, elements[i].substring(2, elements[i].length()), attributeSetThree);
						} catch (BadLocationException e) {
							System.out.println("Bad Location, getListPanel" + e.toString());
						}
						// For other, normal rows
					} else {
						try {
							doc.insertString(0, '\n' + elements[i].substring(0, 2), attributeSetOne);
							doc.insertString(3, elements[i].substring(2, elements[i].length()), attributeSetTwo);
						} catch (BadLocationException e) {
							System.out.println("Bad Location, getListPanel" + e.toString());
						}
					}
					// Next, going through rows with numbers > 9
				} else {
					// the same operation for rows with tips, just "," moved to 8th position in
					// input string
					if (String.valueOf(elements[i].charAt(8)).equals(",")) {
						try {
							doc.insertString(0, '\n' + elements[i].substring(0, 2), attributeSetOne);
							doc.insertString(3, elements[i].substring(2, elements[i].length()), attributeSetThree);
						} catch (BadLocationException e) {
							System.out.println("Bad Location, getListPanel" + e.toString());
						}
					} else {
						try {
							doc.insertString(0, '\n' + elements[i].substring(0, 3), attributeSetOne);
							doc.insertString(4, elements[i].substring(3, elements[i].length()), attributeSetTwo);
						} catch (BadLocationException e) {
							System.out.println("Bad Location, getListPanel" + e.toString());
						}
					}
				}
			}
		}

		// Adding here the first row
		try {
			doc.insertString(0, "Crossword puzzle instructions:" + '\n', attributeSetOne);
		} catch (BadLocationException e) {
			System.out.println("Bad Location, adding Title, List" + e.toString());
		}

		pane.setEditable(false);
		panel.add(pane);
		panel.setPreferredSize(new Dimension(300, 700));
		return panel;

	}

	// his method is created to access the panel from our FinalView class
	protected JPanel returnPanel() {
		List panellist = new List();
		return panellist.getListPanel();
	}
}