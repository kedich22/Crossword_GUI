package checker;

import javax.swing.*;

import java.awt.*;

import gui.CheckField;
import gui.FinalView;
import opener.OpenCrossword;

// The class to check to input answer field based on the input answer
public class CheckAnswer {

	// Creation of objects for accessing methods
	// CheckField to access the inputed answer, OpenCrossword to access the correct
	// answer
	// and FinalView to set the parameters of JDialog
	CheckField answer = new CheckField();
	OpenCrossword answerCheck = new OpenCrossword();
	FinalView view = new FinalView();

	// The method to return the final JDialog after answer check
	public JDialog answerChecker() {

		// Creation of JDialog and panel to contain text
		JDialog result = new JDialog(view.getF(), "Your result");
		JPanel text = new JPanel();

		result.setLayout(new GridBagLayout());

		// The condition to check the correctness of the answer
		if (answer.getButtonLetter().equals(answerCheck.getAnswerName())) {
			JLabel label1 = new JLabel("Congratulations! The crossword puzzle is solved!");
			label1.setFont(new Font("Dialog", Font.ITALIC, 20));
			text.add(label1);
			result.add(text, new GridBagConstraints());
			result.setPreferredSize(new Dimension(550, 100));
		} else {
			JLabel label2 = new JLabel("Answer is incorrect :( Try again");
			label2.setFont(new Font("Dialog", Font.ITALIC, 20));
			text.add(label2);
			result.add(text, new GridBagConstraints());
			result.setPreferredSize(new Dimension(400, 100));
		}

		result.pack();
		result.setLocationRelativeTo(view.getF());
		result.setVisible(true);
		return result;

	}
}


