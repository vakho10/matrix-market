package ge.vakho.matrix_market.part.header.enums;

/**
 * Symmetry is either "general" (legal for real, complex, integer or pattern
 * fields), "symmetric" (real, complex, integer or pattern), "skew-symmetric"
 * (real, complex or integer), or "hermitian" (complex only).
 * 
 * @author vakho
 */
public enum Symmetry {
	GENERAL("general"), SYMMETRIC("symmetric"), SKEW_SYMMETRIC("skew-symmetric"), HERMITIAN("hermitian");

	private String textName;

	private Symmetry(String textName) {
		this.textName = textName;
	}

	public String getTextName() {
		return textName;
	}

	public static Symmetry parseFrom(String textName) {
		switch (textName) {
		case "general":
			return Symmetry.GENERAL;
		case "symmetric":
			return Symmetry.SYMMETRIC;
		case "skew-symmetric":
			return Symmetry.SKEW_SYMMETRIC;
		case "hermitian":
			return Symmetry.HERMITIAN;
		}
		throw new IllegalArgumentException();
	}
}
