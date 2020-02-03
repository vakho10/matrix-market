package ge.vakho.matrix_market;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatrixMarketTest {

	private static byte[] MATRIX;

	@BeforeAll
	static void downloadMatrix() throws MalformedURLException, IOException {
		try (InputStream is = MatrixMarketTest.class.getResourceAsStream("/matrices/valid/small_with_comments.mtx");
				ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			int bytesRead = -1;
			byte[] buffer = new byte[4096];
			while ((bytesRead = is.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
			MATRIX = baos.toByteArray();
		}
	}

	@Test
	@DisplayName("Test root matrix parse")
	public void testParse() throws IOException {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(MATRIX)){
			MatrixMarket mm = MatrixMarket.parseFrom(bais);
			assertNotNull(mm);			
		}
	}
}