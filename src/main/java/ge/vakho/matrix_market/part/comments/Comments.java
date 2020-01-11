package ge.vakho.matrix_market.part.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ge.vakho.matrix_market.part.IPart;

public class Comments implements IPart {

	protected List<String> lines = new ArrayList<>();

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public static Comments parseFrom(List<String> commentLines) {
		Comments comments = new Comments();
		for (String commentLine : commentLines) {
			comments.lines.add(commentLine.substring(1));
		}
		return comments;
	}

	@Override
	public String asText() {
		return lines.parallelStream() //
				.map(line -> "%" + line) //
				.collect(Collectors.joining("\n"));
	}
}