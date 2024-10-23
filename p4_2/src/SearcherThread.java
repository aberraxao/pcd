import javax.swing.*;

class SearcherThread extends Thread {
    private TextRepository textRepository;

    public SearcherThread(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @Override
    public void run() {
        // Enquanto houver chunks
        // Remove um chunk (getChunks)
        // Process o chunk => Imprimir todas as posições encontradas

        TextChunk chunk = textRepository.getChunk();
        while (chunk != null) {
            processChunk(chunk);

            // Devolver o chunk analisado
            textRepository.submitResult(chunk); // TODO versao 2

            // Avança para o próximo chunk
            chunk = textRepository.getChunk();
        }
    }

    private void processChunk(TextChunk chunk) {
        // Imprime todas as posições encontradas

        int fromIndex = 0;
        while (fromIndex < chunk.text.length()) {
            int foundPos = chunk.text.indexOf(chunk.stringToBeFound, fromIndex);

            if (foundPos < 0) break;
            // System.out.println(getName() + " found at " + (chunk.getInitialPos() + foundPos)); // versao 1
            chunk.addFoundPos(chunk.getInitialPos() + foundPos); // versao 2 - adiciona as posições encontradas ao chunk
            fromIndex = foundPos + chunk.stringToBeFound.length();
        }
    }
}
