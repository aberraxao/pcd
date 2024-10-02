package ex1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {
    // Thread safe - lock

    private int count = 0;
    private Lock lock = new ReentrantLock();

    public int get() {
        return count;
    }

    public void increment() {
        // count = count + 1;
        // READ variable count
        // MODIFY add 1
        // WRITE variable count (new value)
        // Como isto são 3 operações pode acontecer várias threads acederem a este contador ao mesmo tempo e fazer overwrite
        // Solução: colocar um cadeado. Com o cadeado apenas 1 thread pode estar dentro do lock de cada vez.
        // As restantes threads ficam em espera
        lock.lock();
        try {
            // Cuidado com as exceções dentro do lock para não bloquear as outras threads
            // Solução: colocar um try... finally caso haja essa possibilidade
            count++;
        } finally {
            lock.unlock();
        }
    }


    private static class ThreadCounter extends Thread {
        private CounterLock shared;

        public ThreadCounter(int id, CounterLock shared) {
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
        CounterLock shared = new CounterLock(); // Objeto partilhado

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
