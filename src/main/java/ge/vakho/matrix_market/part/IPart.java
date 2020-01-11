package ge.vakho.matrix_market.part;

import java.io.OutputStream;
import java.io.PrintWriter;

public interface IPart {
	
	String asText();
	
	default void writeTo(OutputStream os) {
		try (PrintWriter pw = new PrintWriter(os)) {
			pw.println(asText());
		}
	}
}
