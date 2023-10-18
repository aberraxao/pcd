package ex1;

import java.util.LinkedList;

/**
 * Classe do buffer partilhado
 * - put() - synchronized
 * - get() - synchronized
 */
public class BufferMesa {

    public static final int CAPACIDADE = 10;
    private LinkedList<Javali> mesa = new LinkedList<>();

    public synchronized void put(Javali j) throws InterruptedException { // Cadeado "this"
        while (mesa.size() == CAPACIDADE) { // Enquanto cheia
            this.wait();
        }
        // Adicionar o javali
        mesa.add(j);
        // Notificar que ha um novo javali na mesa (direcionando para o Glutoes)
        this.notifyAll();
    }

    public synchronized Javali get() throws InterruptedException {
        while (mesa.isEmpty()) // Enquanto vazio
            this.wait();

        // Remover
        Javali j = mesa.remove(0);
        // Notificar que ha novo espaco na mesa (direcionado para os Cozinheiros)
        this.notifyAll();
        // Devolver javali
        return j;
    }
}