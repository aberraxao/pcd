package ex1;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe do buffer
 * - Partilhado: sincronizar usando mutex (cadeado) "this"
 * - Limitado: capacidade = 10 javalis
 * - put() - synchronized
 * - get() - synchronized
 */
public class BufferMesa {

    public static final int CAPACIDADE = 10;
    private List<Javali> buffer = new LinkedList<Javali>();

    /**
     * Insere um javali na mesa (método bloqueante, isto é, fica à espera)
     * Ação: inserir na mesa
     * Condição: quando a mesa não está cheia (notFull)
     * (enquanto cheia, wait)
     *
     * @thows InterruptedException. Delega para a thread que invocou o método
     */
    public synchronized void put(Javali j) throws InterruptedException { // Cadeado "this"
        // Condição para agir: notFull
        while (buffer.size() == CAPACIDADE) {
            this.wait();
        }
        buffer.add(j);

        // Notificar que ha um novo javali na mesa
        // Interessado pela notificação: Gaulês
        this.notifyAll();
    }

    /**
     * Remove um javali na mesa (método bloqueante)
     * Ação: remover da mesa
     * Condição: Mesa não está vazia (notEmpty)
     * (enquanto vazia, wait)
     *
     * @return Devolve o javali removido
     * @thows InterruptedException. Delega para a thread que invocou o método
     */
    public synchronized Javali get() throws InterruptedException {
        // Condição para agir: notEmpty
        while (buffer.isEmpty())
            this.wait();
        Javali j = buffer.remove(0);

        // Notificar que ha novo espaco na mesa
        // Interessado pela notificação: Cozinheiro
        this.notifyAll();
        return j;
    }
}