package ge.vakho.matrix_market.part.header.enums;

/**
 * Field is either "real", "double", "complex", "integer" or "pattern".
 * 
 * @author vakho
 */
public enum Field {
	REAL("real"), DOUBLE("double"), COMPLEX("complex"), INTEGER("integer"), PATTERN("pattern");
	
	private String textName;

	private Field(String textName) {
		this.textName = textName;
	}

	public String getTextName() {
		return textName;
	}

	public static Field parseFrom(String textName) {
		switch (textName) {
		case "real":
			return Field.REAL;
		case "double":
			return Field.DOUBLE;
		case "complex":
			return Field.COMPLEX;
		case "integer":
			return Field.INTEGER;
		case "pattern":
			return Field.PATTERN;
		}
		throw new IllegalArgumentException();
	}
}