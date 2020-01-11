package ge.vakho.matrix_market;

import java.io.IOException;
import java.io.InputStream;
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
import ge.vakho.matrix_market.part.size.Size;

public class MatrixMarket implements IPart {

	protected Header header;
	protected Comments comments;
	protected Size size;
	protected Data data;

	public static MatrixMarket parseFrom(Path mtxFile) throws IOException {

		try (InputStream is = Files.newInputStream(mtxFile);
				RandomAccessFile raf = new RandomAccessFile(mtxFile.toFile(), "r")) {

			MatrixMarket mm = new MatrixMarket();

			// Header
			String headerLine = raf.readLine();
			mm.header = Header.parseFrom(headerLine);

			// Comments
			// TODO skip empty lines
			String line;
			List<String> commentLines = new ArrayList<>();
			while ((line = raf.readLine().trim()).startsWith("%")) {
				commentLines.add(line);
			}
			mm.comments = Comments.parseFrom(commentLines);

			// Size line
			mm.size = Size.parseFrom(line);

			// Data lines
			List<String> dataLines = new ArrayList<>();
			while ((line = raf.readLine()) != null && !line.trim().isEmpty()) {
				dataLines.add(line);
			}
			mm.data = Data.parseFrom(dataLines);

			return mm;
		}
	}

	@Override
	public String asText() {
		return List.of(header.asText(), comments.asText(), size.asText(), data.asText()).parallelStream() //
				.collect(Collectors.joining("\n"));
	}

	@Override
	public String toString() {
		return "MatrixMarket [header=" + header + ", comments=" + comments + ", size=" + size + ", data=" + data + "]";
	}
}