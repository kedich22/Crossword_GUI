package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import checker.CheckAnswer;
import importdata.Reading;
import opener.OpenCrossword;

// The class that creates a field to put final answer
public class CheckField {

	// creation of other classes objects to access Reading and OpenCrossword methods
	// Reading methods to set dimensions of buttons and OpenCrossword to access the
	// answer
	static OpenCrossword answer = new OpenCrossword();
	Reading file = new Reading();

	// Method to get the length of answer for further creation of cells
	private static int getAnswerLength() {
		return answer.getAnswerName().length();
	}

	// Creation of static arrays for buttons and the array to store letters
	static JButton[] button = new JButton[CheckField.getAnswerLength()];
	static String[] letter = new String[CheckField.getAnswerLength()];

	// Initialization of new listener object
	CheckerListener listen = new CheckerListener();

	// Initialization of used panels, one final and several inside panels
	JPanel f = new JPanel();
	JPanel panel = new JPanel();
	JPanel check = new JPanel();

	// Method that allows to change button letters and assigns the letter to answer
	// field
	// Is accessed from keyboard class
	protected static void setButton(int i, String text) {
		button[i].setText(text);
		letter[i] = text;
	}

	// Method that created a final JPanel and returns it
	protected JPanel checkFieldPanel() {

		f.setLayout(new FlowLayout());
		panel.setLayout(new GridLayout(1, CheckField.getAnswerLength()));

		// Creation of n-cells for answer field through loop
		for (int i = 0; i < CheckField.getAnswerLength(); i++) {
			button[i] = new JButton();
			button[i].setFont(new Font("Arial", Font.PLAIN, 18));
			int ii = i;
			button[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					new Keyboard(ii).keyboardCheck();
				}
			});
			button[i].setFocusPainted(false);
			button[i].setContentAreaFilled(true);
			button[i].setBackground(Color.GRAY);
			button[i].setOpaque(true);
			panel.add(button[i]);
		}
		panel.setPreferredSize(
				new Dimension(725 * CheckField.getAnswerLength() / file.getCols(), 725 / file.getRows()));

		JButton checkButton = new JButton("Check");
		checkButton.addActionListener(listen);
		check.add(checkButton);

		check.setPreferredSize(new Dimension(150, 30));

		f.add(panel);
		f.add(check);
		f.setPreferredSize(new Dimension(500, 800 / file.getRows()));
		return f;
	}

	// Method that combines one string from separate letters in array
	public String getButtonLetter() {
		String letternew = new String();
		for (int i = 0; i < CheckField.getAnswerLength(); i++) {
			letternew = letternew + letter[i];
		}
		return letternew;
	}

	// Listener for the button to check the answer
	private class CheckerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new CheckAnswer().answerChecker();
		}
	}

	// Method that returns the panel for final gui
	protected JPanel returnPanel() {
		CheckField panelField = new CheckField();
		return panelField.checkFieldPanel();
	}
}
