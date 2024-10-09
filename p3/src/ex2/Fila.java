package ex2;


public class Fila extends Thread {

    // not complete

    private int[] fila;
    private int primeiro = -1;
    private int ultimo = -1;
    private int tamanho = 0;
    private int capacidade;

    public Fila(int capacidade) {
        this.fila = new int[capacidade];
        this.capacidade = capacidade;
    }

    private boolean empty() {
        // Verifica se a fila está vazia
        return tamanho == 0;
    }

    private int peek() {
        // Devolve o número no início da fila.
        // Lança IllegalStateException caso a fila esteja vazia.
        if (empty()) throw new IllegalStateException();
        return fila[primeiro];
    }

    private int pool() {
        // Retira e devolve o número no início da fila. Lança IllegalStateException caso a fila esteja vazia.
        if (empty()) throw new IllegalStateException();
        tamanho--;
        return fila[primeiro--];
    }

    private void offer(int item){
        // Adiciona o item no fim da fila. Lança IllegalStateException caso a fila esteja cheia.
        if (tamanho == ultimo) throw new IllegalStateException();
        if (primeiro == -1) primeiro++;
        fila[++ultimo] = item;
        tamanho++;
    }

    private int size(){
        // devolve o numero de elementos que existem na fila.
        return tamanho;
    }


    private static class ThreadFila extends Thread {
        private Fila shared;

        public ThreadFila(int id, Fila shared) {
            super("Th " + id);
            this.shared = shared;

        }

        @Override
        public void run() {
            // Repete 1000 vezes
            for (int i = 0; i < 1000; i++) {
                shared.offer(i);
            }
        }


        public static void main(String[] args) throws InterruptedException {
            Fila shared = new Fila(10000); // Objeto partilhado

            ThreadFila[] threads = new Fila.ThreadFila[6];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new ThreadFila(i, shared);
            }

            // Starts all threads
            for (ThreadFila t : threads) {
                t.start();
            }

            // Waits for all threads to end
            for (ThreadFila t : threads) {
                t.join(); // This throws InterruptedException
            }
        }
    }
}