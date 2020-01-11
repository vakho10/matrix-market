package ge.vakho.matrix_market.part.header.enums;

/**
 * Format is either "coordinate" or "array"; 
 * 
 * @author vakho
 */
public enum Format {
	COORDINATE("coordinate"), ARRAY("array");
	
	private String textName;

	private Format(String textName) {
		this.textName = textName;
	}

	public String getTextName() {
		return textName;
	}

	public static Format parseFrom(String textName) {
		switch (textName) {
		case "coordinate":
			return Format.COORDINATE;
		case "array":
			return Format.ARRAY;
		}
		throw new IllegalArgumentException();
	}
}