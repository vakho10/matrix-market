package ge.vakho.matrix_market.part.header.enums;

/**
 * Object is usually "matrix", and that is the case we will consider here.
 * Another legal value is "vector", whose format is similar, but with some
 * obvious simplifications.
 * 
 * @author vakho
 */
public enum Object {
	MATRIX("matrix"), VECTOR("vector");

	private String textName;

	private Object(String textName) {
		this.textName = textName;
	}

	public String getTextName() {
		return textName;
	}

	public static Object parseFrom(String textName) {
		switch (textName) {
		case "matrix":
			return Object.MATRIX;
		case "vector":
			return Object.VECTOR;
		}
		throw new IllegalArgumentException();
	}
}