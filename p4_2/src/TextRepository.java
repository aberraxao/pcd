import java.util.LinkedList;
import java.util.List;

public class TextRepository {
    List<TextChunk> chunkToAnalyze;
    List<TextChunk> chunksAnalyzed = new LinkedList<TextChunk>();
    int numTotalChunks;

    public TextRepository(String text, String stringToBeFound, int chunkSize) {
        chunkToAnalyze = new LinkedList<TextChunk>();
        // Criar os chunks
        for (int initPos = 0; initPos < text.length(); initPos = initPos + chunkSize) {
            int finalPos = Math.min(initPos + chunkSize, text.length());
            TextChunk chunk = new TextChunk(text.substring(initPos, finalPos), initPos, stringToBeFound);
            chunkToAnalyze.add(chunk);
        }
        numTotalChunks = chunkToAnalyze.size();

    }

    public synchronized TextChunk getChunk() {
        // Remover um chunk da lista
        // syncronized para nao ter o risco de remover o mesmo chunk duas vezes
        // Este método não é bloqueante => se não houver devolve null

        if (chunkToAnalyze.isEmpty()) return null;

        return chunkToAnalyze.remove(0);
    }

    // TODO metodo exclusivo da versao 2
    public synchronized void submitResult(TextChunk chunk) {
        // Recebe um chunk analizado
        chunksAnalyzed.add(chunk);

        // Notificar todos os chunks que já foram analizados
        if (chunksAnalyzed.size() == numTotalChunks)
            notifyAll();

    }

    // TODO metodo exclusivo da versao 2
    public synchronized List<Integer> getAllMatches() throws InterruptedException {
        while (chunksAnalyzed.size() < numTotalChunks) wait();

        return summaryResults();

    }

    // TODO metodo exclusivo da versao 2
    private List<Integer> summaryResults() {
        List<Integer> results = new LinkedList<>();

        for (TextChunk chunk : chunksAnalyzed) {
            // Verificar as posições => se houver porições, adiciona na lista results
            results.addAll(chunk.getFoundPos());
        }

        return results;
    }
}
