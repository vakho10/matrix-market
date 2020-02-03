package ge.vakho.matrix_market;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ge.vakho.matrix_market.part.header.Header;
import ge.vakho.matrix_market.part.header.enums.Field;
import ge.vakho.matrix_market.part.header.enums.Format;
import ge.vakho.matrix_market.part.header.enums.Object;
import ge.vakho.matrix_market.part.header.enums.Symmetry;

public class HeaderTest {

	static String HEADER;
	
	@BeforeAll
	static void init() {
		HEADER = "%%MatrixMarket matrix coordinate real symmetric";
	}
	
	@Test
	@DisplayName("Test normal header matrix parse")
	public void testNormalHeader() throws IOException {
		assertNotNull(Header.parseFrom(HEADER));
	}

	@Test
	@DisplayName("Test no header matrix parse")
	public void testNoHeader() throws IOException {
		assertThrows(IllegalArgumentException.class, () -> Header.parseFrom("some random text and no header :("));
	}

	@Test
	@DisplayName("Test header object parse")
	public void testObjectParse() throws IOException {
		Header header = Header.parseFrom(HEADER);
		assertEquals(Object.MATRIX, header.getObject());
	}
	
	@Test
	@DisplayName("Test header format parse")
	public void testFormatParse() throws IOException {
		Header header = Header.parseFrom(HEADER);
		assertEquals(Format.COORDINATE, header.getFormat());
	}
	
	@Test
	@DisplayName("Test header field parse")
	public void testFieldParse() throws IOException {
		Header header = Header.parseFrom(HEADER);
		assertEquals(Field.REAL, header.getField());
	}
	
	@Test
	@DisplayName("Test header format parse")
	public void testSymmetryParse() throws IOException {
		Header header = Header.parseFrom(HEADER);
		assertEquals(Symmetry.SYMMETRIC, header.getSymmetry());
	}
}