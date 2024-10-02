package ex1;

public class CounterSynch {
    // Thread safe - utilizando o cadeado intrínseco (mutex)
    // Nota: qualquer objeto é cadeado
    // Há que validar se as classes que estamos a usar são thread safe

    private int count = 0;

    public int get() {
        return count;
    }

    public void increment() {
        // count = count + 1;
        // READ variable count
        // MODIFY add 1
        // WRITE variable count (new value)
        // Como isto são 3 operações pode acontecer várias threads acederem a este contador ao mesmo tempo e fazer overwrite
        // Solução: colocar um cadeado no this que envolva o bloco problemático
        // Alternativa: colocar o synchronized no método (synchronized public void increment())
        synchronized (this) {
            count++;
        }
    }


    private static class ThreadCounter extends Thread {
        private CounterSynch shared;

        public ThreadCounter(int id, CounterSynch shared){
            super("Th " + id);
            this.shared = shared;
        }

        @Override
        public void run() {
            // Repete 1000 vezes
            for (int i = 0; i < 1000; i++) {
                shared.increment();
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {
        CounterSynch shared = new CounterSynch(); // Objeto partilhado

        ThreadCounter[] threads = new ThreadCounter[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadCounter(i, shared);
        }

        // Starts all threads
        for (ThreadCounter t : threads) {
            t.start();
        }

        // Waits for all threads to end
        for (ThreadCounter t : threads) {
            t.join(); // This throws InterruptedException
        }
        // We expect counter = 4000, because we have protected the counter
        System.out.println("Counter value: " + shared.get());
    }
}
