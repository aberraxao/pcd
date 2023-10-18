package ex1;

/**
 * Classe consumidora - THREAD
 * - run() - consumir um numero fixo de javalis
 */
public class Glutao extends Thread {
    private int id; // Identificador
    private int max; // Numero de javalis a consumir
    private BufferMesa mesa; // Buffer partilhado

    public Glutao(int id, int max, BufferMesa mesa) {
        super("Glutao-" + id);
        this.id = id;
        this.max = max;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            for (; i < max; i++) {
                Javali j = mesa.get();
                System.out.println("Consumido por " + getName() + " " + j);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(getName() + " interrompida");
        }
        System.out.println(getName() + " - consumiu " + i + " javalis");
    }
}
