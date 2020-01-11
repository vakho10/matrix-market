package ge.vakho.matrix_market;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	private static final int BUFFER_SIZE = 4096;

	public static void main(String[] args) throws MalformedURLException, IOException {

		// Download the matrix
		URLConnection conn = new URL("https://people.sc.fsu.edu/~jburkardt/data/mm/gr_900_900_crg.mm").openConnection();
		Path tmpFile = Files.createTempFile("mm-", ".mtx");
		tmpFile.toFile().deleteOnExit();

		// Save to the temporary file
		try (InputStream is = conn.getInputStream(); FileOutputStream fos = new FileOutputStream(tmpFile.toFile())) {
			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = is.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}
		}

		// Parse from file
		MatrixMarket mm = MatrixMarket.parseFrom(tmpFile);
		System.out.println(mm);
//		System.out.println(MMUtils.isPositiveDefinite(mm));
		
		// Write to some temporary file...
		Path outputTmpFile = Files.createTempFile("mm-", ".mtx");
		try (OutputStream os = Files.newOutputStream(outputTmpFile)) {
			mm.writeTo(os);			
		}
		System.out.println("See and don't forget to delete: " + outputTmpFile);
	}
}