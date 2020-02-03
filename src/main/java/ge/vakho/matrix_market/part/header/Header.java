package ge.vakho.matrix_market.part.header;

import static com.google.common.base.Preconditions.checkArgument;
import com.google.common.base.Strings;

import ge.vakho.matrix_market.part.IPart;
import ge.vakho.matrix_market.part.header.enums.Field;
import ge.vakho.matrix_market.part.header.enums.Format;
import ge.vakho.matrix_market.part.header.enums.Object;
import ge.vakho.matrix_market.part.header.enums.Symmetry;

/**
 * The header line has the form: <br />
 * 
 * %%MatrixMarket object format field symmetry
 * 
 * @author vakho
 */
public class Header implements IPart {

	protected Object object;
	protected Format format;
	protected Field field;
	protected Symmetry symmetry;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Symmetry getSymmetry() {
		return symmetry;
	}

	public void setSymmetry(Symmetry symmetry) {
		this.symmetry = symmetry;
	}

	public static Header parseFrom(String headerString) {

		checkArgument(!Strings.isNullOrEmpty(headerString), "Expected non-empty header string");

		// TODO Write checks for valid header string!
		String[] headerStrings = headerString.split("\\s+");

		Header header = new Header();
		header.object = Object.parseFrom(headerStrings[1]);
		header.format = Format.parseFrom(headerStrings[2]);
		header.field = Field.parseFrom(headerStrings[3]);
		header.symmetry = Symmetry.parseFrom(headerStrings[4]);
		return header;
	}

	@Override
	public String asText() {
		return "%%MatrixMarket " + object.getTextName() + " " + format.getTextName() + " " + field.getTextName() + " "
				+ symmetry.getTextName();
	}
}