package ex1_vdeadlock;

/**
 * Classe consumidora - THREAD
 * - Tarefa run() - consumir um numero fixo de javalis (remove os javalis do buffer partilhado)
 */
public class Gaules extends Thread {
    private int id; // Identificador
    private int max; // Numero de javalis a consumir
    private BufferMesa mesa; // Buffer partilhado

    public Gaules(int id, int max, BufferMesa mesa) {
        super("Glutao-" + id);
        this.id = id;
        this.max = max;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (i < max || max == -1) {
                i++;
                Javali j = mesa.get();
                System.out.println("Consumido por " + getName() + " " + j);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(getName() + " interrompida");
        }
        System.out.println(getName() + " - consumiu " + i + " javalis");
    }
}
