package ge.vakho.matrix_market;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ge.vakho.matrix_market.part.IPart;
import ge.vakho.matrix_market.part.comments.Comments;
import ge.vakho.matrix_market.part.data.Data;
import ge.vakho.matrix_market.part.header.Header;

public class MatrixMarket implements IPart {

	protected Header header;
	protected Comments comments;
	protected Data data;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public static MatrixMarket parseFrom(InputStream inputStream) throws IOException {

		try (InputStreamReader isr = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(isr)) {

			MatrixMarket mm = new MatrixMarket();

			// Header
			String headerLine = br.readLine();
			mm.header = Header.parseFrom(headerLine);

			// Comments
			// TODO skip empty lines
			String line;
			List<String> commentLines = new ArrayList<>();
			while ((line = br.readLine().trim()).startsWith("%")) {
				commentLines.add(line);
			}
			mm.comments = Comments.parseFrom(commentLines);

			// Size line and data lines
			List<String> dataLines = new ArrayList<>();
			dataLines.add(line);
			while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
				dataLines.add(line);
			}
			mm.data = Data.parseFrom(dataLines);
			
			return mm;
		}
	}

	@Override
	public String asText() {
		return List.of(header.asText(), comments.asText(), data.asText()).parallelStream() //
				.collect(Collectors.joining("\n"));
	}
}