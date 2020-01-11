package ge.vakho.matrix_market.part.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ge.vakho.matrix_market.part.IPart;

public class Data implements IPart {

	protected List<Entry> entries = new ArrayList<>();

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public static class Entry implements IPart {

		protected int rowNumber;
		protected int columnNumber;
		protected double value;

		public int getRowNumber() {
			return rowNumber;
		}

		public void setRowNumber(int rowNumber) {
			this.rowNumber = rowNumber;
		}

		public int getColumnNumber() {
			return columnNumber;
		}

		public void setColumnNumber(int columnNumber) {
			this.columnNumber = columnNumber;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public static Entry parseFrom(String dataLine) {
			Entry entry = new Entry();
			String[] dataEntries = dataLine.split("\\s+");
			entry.rowNumber = Integer.parseInt(dataEntries[0]);
			entry.columnNumber = Integer.parseInt(dataEntries[1]);
			entry.value = Double.parseDouble(dataEntries[2]);
			return entry;
		}

		@Override
		public String asText() {
			return rowNumber + " " + columnNumber + " " + value;
		}
	}

	public static Data parseFrom(List<String> dataLines) {
		Data data = new Data();
		for (String dataLine : dataLines) {
			data.entries.add(Entry.parseFrom(dataLine));
		}
		return data;
	}

	@Override
	public String asText() {
		return entries.parallelStream() //
				.map(entry -> entry.asText()) //
				.collect(Collectors.joining("\n"));
	}
}