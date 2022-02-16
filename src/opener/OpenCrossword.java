package opener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import gui.FinalView;

// The final class just to open the final panel and set source and answer 
public class OpenCrossword {

	// Initializing of source and answer
	private String sourceName = "puzzle-1-adjusted.txt";
	private String answerName = "SOUTHPARK";

	// Getter and setter methods for name and answer
	public String getSourceName() {
		return sourceName;
	}

	public String getAnswerName() {
		return answerName;
	}

	// main method that runs created frame
	public static void main(String[] args) {
		// the next try/catch block is focused on the setting up Look And Feel
		// for the same representation between different platforms
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			System.out.println("Look and Feel not set" + e.toString());
		}
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new FinalView().run();
				}
			});
		}

	}
}