package ex1;

/**
 * Classe produtora - THREAD
 * - run() - produzir um numero fixo de javalis e coloca-los na mesa
 */
public class Cozinheiro extends Thread {
    private int id; // Identificador
    private int max; // Numero de javalis a produzir
    private BufferMesa mesa; // Buffer partilhado

    public Cozinheiro(int id, int max, BufferMesa mesa) {
        super("Cozinheiro-" + id);
        this.id = id;
        this.max = max;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            for (; i < max; i++) {
                Javali j = new Javali(id, i);
                mesa.put(j);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(getName() + " interrompida");
        }
        System.out.println(getName() + " - produziu " + i + " javalis");
    }
}

