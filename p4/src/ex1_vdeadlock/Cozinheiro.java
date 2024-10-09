package ex1_vdeadlock;

/**
 * Classe produtora - THREAD => tem que ter uma tarefa
 * - Tarefa run() - produzir um número fixo de javalis e colocá-los na mesa (buffer partilhado)
 */
public class Cozinheiro extends Thread {
    private int id; // Identificador
    private int max; // Número de javalis a produzir
    private BufferMesa mesa; // Buffer partilhado

    Cozinheiro(int id, int max, BufferMesa mesa) {
        super("Cozinheiro-" + id);
        this.id = id;
        this.max = max;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (i < max || max==-1) {
                i++;
                // tenho um try, pq o método put é bloqueante (wait)
                Javali j = new Javali(id, i);
                mesa.put(j);
                // System.out.println(getName() + " - produziu o javali " + j);
            }
        } catch (InterruptedException e) {
            // Imprime a exceção e segue para o próximo javali
            e.printStackTrace();
        }
        System.out.println(getName() + " - produziu " + i + " javalis");
    }
}

