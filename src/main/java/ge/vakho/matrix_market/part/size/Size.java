package ge.vakho.matrix_market.part.size;

import ge.vakho.matrix_market.part.IPart;

/**
 * The header line has the form: <br />
 * 
 * %%MatrixMarket object format field symmetry
 * 
 * @author vakho
 */
public class Size implements IPart {

	protected int numberOfRows;
	protected int numberOfColumns;
	protected int numberOfEntries;

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public int getNumberOfEntries() {
		return numberOfEntries;
	}

	public void setNumberOfEntries(int numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}

	public static Size parseFrom(String sizeLine) {

		// TODO Write checks for valid header string!
		String[] sizes = sizeLine.split("\\s+");

		Size size = new Size();
		size.numberOfRows = Integer.parseInt(sizes[0]);
		size.numberOfColumns = Integer.parseInt(sizes[1]);
		size.numberOfEntries = Integer.parseInt(sizes[2]);
		return size;
	}

	@Override
	public String asText() {
		return numberOfRows + " " + numberOfColumns + " " + numberOfEntries;
	}
	
	@Override
	public String toString() {
		return "Size [numberOfRows=" + numberOfRows + ", numberOfColumns=" + numberOfColumns + ", numberOfEntries="
				+ numberOfEntries + "]";
	}
}