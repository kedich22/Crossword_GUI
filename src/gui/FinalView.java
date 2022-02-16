package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.*;

import javax.swing.*;

// Class that creates a final graphic visualization from all panels
public class FinalView {

	// Creation of new frame
	JFrame f = new JFrame("Swedish-style Crossword Puzzle");

	// method to get our created frame from other classes, for positioning of
	// JDialog and for final output
	public JFrame getF() {
		return f;
	}

	// main method that combines other panels and makes final visualization
	public void run() {
		FinalView frame = new FinalView();

		// Initiation of new JPanels
		JPanel panelText = new JPanel();
		JPanel panelField = new JPanel();
		JPanel finalPanel = new JPanel();
		JPanel panelCheck = new JPanel();
		JPanel checkAndField = new JPanel();

		// Creation of objects from other classes (to import our panels)
		List list = new List();
		Field field = new Field();
		CheckField check = new CheckField();

		// Import and set up of panels

		panelText = list.returnPanel();
		panelField = field.returnPanel();
		panelCheck = check.returnPanel();

		checkAndField.setLayout(new BorderLayout());
		finalPanel.setLayout(new BorderLayout());

		checkAndField.add(panelField, BorderLayout.CENTER);
		checkAndField.add(panelCheck, BorderLayout.PAGE_END);

		panelText.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		checkAndField.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 20));

		finalPanel.add(panelText);
		finalPanel.add(checkAndField, BorderLayout.LINE_END);

		// Setting parameters of Frame

		frame.getF().setContentPane(finalPanel);
		frame.getF().setVisible(true);
		frame.getF().setPreferredSize(new Dimension(980, 825));
		frame.getF().pack();
		frame.getF().setLocationRelativeTo(null);
		frame.getF().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
