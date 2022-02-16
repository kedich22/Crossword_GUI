package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

// This class creates different keyboards that will be used to put letter into crossword
// It contains several different Keyboard constructors, that allows to use it in case of different matrixes
public class Keyboard {

	// Creation of used instant variables
	private JDialog frame;
	private JPanel keyPanel;
	// The following variables are used to store positions of matrix cells
	private int rownum;
	private int colnum;
	private int num;
	private String clue;
	FinalView view = new FinalView();

	// Creation of 2d string with alphabetic letters
	private String[][] alphabet = { { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" },
			{ "A", "S", "D", "F", "G", "H", "J", "K", "L" }, { "Z", "X", "C", "V", "B", "N", "M" } };

	// Creation of object listeners
	private CustomButtonListener listen = new CustomButtonListener();
	private CustomButtonListenerTwo listenpanel = new CustomButtonListenerTwo();

	// The method that obtains random numbers for later letter extractions
	private String getRandomLetter() {
		Random num = new Random();
		int row = num.nextInt(2);
		int col;
		if (row == 0) {
			col = num.nextInt(9);
		} else if (row == 1) {
			col = num.nextInt(8);
		} else {
			col = num.nextInt(6);
		}
		return alphabet[row][col];
	}

	// First constructor used for white squares, with two input variables - position
	// of certain cell in 2d matrix

	protected Keyboard(int row, int col) {
		this.rownum = row;
		this.colnum = col;
	}

	// Method to create a JDiaolog for first type of Keyboard
	protected JDialog keyboardField() {

		this.frame = new JDialog(view.f, "Enter a character"); // ("Enter a character: ");
		this.keyPanel = new JPanel();
		this.keyPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		// The following loop creates the alphabet buttons
		int i;
		int j;
		for (i = 0; i < alphabet.length; i++) {
			for (j = 0; j < alphabet[i].length; j++) {
				JButton button = new JButton(alphabet[i][j]);
				button.addActionListener(listen); // access to first action listener
				button.setFocusPainted(false);
				button.setContentAreaFilled(false);
				c.gridx = j;
				c.gridy = i;
				this.keyPanel.add(button, c);
			}
		}

		frame.add(this.keyPanel, BorderLayout.PAGE_START);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // changed
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(view.getF());

		return frame;

	}

	// The next constructor is used for blue squares, also for input the clue letter
	// as a string accepted
	protected Keyboard(int row, int col, String clue) {
		this.rownum = row;
		this.colnum = col;
		this.clue = clue;
	}

	// The method to create keyboard with clue letters
	protected JDialog keyboardFieldClue() {
		this.frame = new JDialog(view.f, "Enter a character");
		this.keyPanel = new JPanel();
		this.keyPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		// The next loop uses method getRandomNumber to obtain 4 random letters from out
		// alphabet array
		String[] fourLetters = new String[4];
		int k = 0;
		while (k != 4) {
			String letter1 = getRandomLetter();
			String letter2 = getRandomLetter();
			String letter3 = getRandomLetter();
			String letter4 = getRandomLetter();
			if (!clue.equals(letter1)) {
				fourLetters[0] = letter1;
				k++;
				if (!clue.equals(letter2) && !fourLetters[0].equals(letter2)) {
					fourLetters[1] = letter2;
					k++;
					if (!clue.equals(letter3) && !fourLetters[0].equals(letter3) && !fourLetters[1].equals(letter3)) {
						fourLetters[2] = letter3;
						k++;
						if (!clue.equals(letter4) && !fourLetters[0].equals(letter4) && !fourLetters[1].equals(letter4)
								&& !fourLetters[2].equals(letter4)) {
							fourLetters[3] = letter4;
							k++;
						} else
							k = 0;
					} else
						k = 0;
				} else
					k = 0;
			} else
				k = 0;
		}

		// The following loop creates the keyboard buttons and setting active only 5
		// (one clue button and 4 random)
		int i;
		int j;
		for (i = 0; i < alphabet.length; i++) {
			for (j = 0; j < alphabet[i].length; j++) {
				if (alphabet[i][j].equals(clue) | alphabet[i][j].equals(fourLetters[0])
						| alphabet[i][j].equals(fourLetters[1]) | alphabet[i][j].equals(fourLetters[2])
						| alphabet[i][j].equals(fourLetters[3])) {
					JButton button = new JButton(alphabet[i][j]);
					button.addActionListener(listen);
					button.setFocusPainted(false);
					button.setContentAreaFilled(false);
					c.gridx = j;
					c.gridy = i;
					this.keyPanel.add(button, c);
				} else {
					JButton button = new JButton(alphabet[i][j]);
					button.addActionListener(listen);
					button.setFocusPainted(false);
					button.setEnabled(false);
					button.setContentAreaFilled(false);
					c.gridx = j;
					c.gridy = i;
					this.keyPanel.add(button, c);
				}

			}
		}

		frame.add(this.keyPanel, BorderLayout.PAGE_START);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(view.getF());

		return frame;

	}

	// The next constructor is used for creation of Keyboard for our final answer
	// and accepts only one input - position in a String
	protected Keyboard(int num) {
		this.num = num;
	}

	// Method to create keyboard for check field below the matrix
	protected JDialog keyboardCheck() {
		this.frame = new JDialog(view.f, "Enter a character"); // ("Enter a character: ");
		this.keyPanel = new JPanel();
		this.keyPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		int i;
		int j;
		for (i = 0; i < alphabet.length; i++) {
			for (j = 0; j < alphabet[i].length; j++) {
				JButton button = new JButton(alphabet[i][j]);
				button.addActionListener(listenpanel); // other listener
				button.setFocusPainted(false);
				button.setContentAreaFilled(false);
				c.gridx = j;
				c.gridy = i;
				this.keyPanel.add(button, c);
			}
		}

		frame.add(this.keyPanel, BorderLayout.PAGE_START);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(view.getF());
		return frame;

	}

	// Listener to change buttons in a matrix field
	private class CustomButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Field.setButton(rownum, colnum, ((JButton) e.getSource()).getText());
			frame.dispose();
		}
	}

	// Listener to change buttons in a answer field
	private class CustomButtonListenerTwo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			CheckField.setButton(num, ((JButton) e.getSource()).getText()); // maybe change to object
			frame.dispose();
		}
	}
}
