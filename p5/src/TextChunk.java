
import java.util.ArrayList;
import java.util.List;

public class TextChunk {
	public final String text; // Texto do chunk
	private final int initialPos; // Posicao do chunk no doc original
	public final String stringToBeFound; // Palavra a procurar
	private List<Integer> foundPos = new ArrayList<>(); // versao 2

	public TextChunk(String text, int initialPos, String stringToBeFound) {
		super();
		this.text = text;
		this.initialPos = initialPos;
		this.stringToBeFound = stringToBeFound;
	}

	public int getInitialPos() {
		return initialPos;
	}

	public List<Integer> getFoundPos() {
		return List.copyOf(foundPos);
	}

	public void addFoundPos(int pos) {
		foundPos.add(pos);
	}

}
