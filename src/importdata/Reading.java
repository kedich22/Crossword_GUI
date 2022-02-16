package importdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import opener.OpenCrossword;

// This class processes the import of data from the text file
public class Reading {

	// Creation of 2 objects, OpenCrossword - to use getter method of source
	OpenCrossword name = new OpenCrossword();
	static Reading scan = new Reading();

	// Method that created a path that will be used to access the file
	private Path getPath() {
		return Paths.get("src/" + name.getSourceName()); // before export just delete the src/
	}

	// preliminary method, where we extract the first row with dimensions and return
	// as a String[] with 2 dimensions
	private String[] getRowsAndCols() {
		String line = null;
		try {
			// line = Files.readAllLines(puzzle).get(0);
			line = Files.readAllLines(scan.getPath()).get(0);
		} catch (IOException e) {
			System.out.println("IO Exception, getRowsAndCols, Reading" + e.toString());
		}
		return line.split(" ");
	}

	// Following methods are created to access the number of rows and columns of our
	// crossword matrix
	public int getRows() { // make not public create object when access
		return Integer.parseInt(scan.getRowsAndCols()[0]);
	}

	public int getCols() {
		return Integer.parseInt(scan.getRowsAndCols()[1]);
	}

	// The following method allows us to read lines and returns all lines with a
	// String[]
	public String[] readingLines() {
		int count = 0;
		int i;
		int j = 0;
		try {
			count = (int) Files.lines(scan.getPath()).count();
		} catch (IOException e) {
			System.out.println("IO Exception, readingLines, Reading" + e.toString());
		}
		String[] line = new String[count - getRows() - 1];
		for (i = getRows() + 1; i < count; i++) {
			try {
				line[j] = Files.readAllLines(scan.getPath()).get(i);
			} catch (IOException e) {
				System.out.println("IO Exception, readingLines, Reading" + e.toString());
			}
			j++;
		}
		return line;
	}

	// The next method is a reading of matrix with cells and writing of result into
	// 2-dimensional matrix
	public String[][] readField() {
		String line = null;
		String[] array = null;
		String[][] twoDarray = new String[getRows()][getCols()];
		int i;
		for (i = 1; i < getRows() + 1; i++) {
			try {
				line = Files.readAllLines(scan.getPath()).get(i);
			} catch (IOException e) {
				System.out.println("IO Exception, readingField, Reading" + e.toString());
			}
			array = line.split(" ");
			twoDarray[i - 1] = array;
		}
		return twoDarray;
	}
}
