package ex1;

public class Counter {
    // No thread safe

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
        count++;
    }


    private static class ThreadCounter extends Thread {
        private Counter shared;

        public ThreadCounter(int id, Counter shared) {
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
        Counter shared = new Counter(); // Objeto partilhado

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
        // We expect counter <=4000, because we might lose some counts
        // We might lose counts because increment is actually doing 3 operations
        System.out.println("Counter value: " + shared.get());
    }
}
