package ex1;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic {
    // Thread safe - usar um objeto thread safe (atómicos)

    private AtomicInteger count = new AtomicInteger(0);


    public int get() {
        return count.get();
    }

    public void increment() {
        count.incrementAndGet();
    }


    private static class ThreadCounter extends Thread {
        private CounterAtomic shared;

        public ThreadCounter(int id, CounterAtomic shared) {
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
        CounterAtomic shared = new CounterAtomic(); // Objeto partilhado

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
        // We expect counter = 4000, because the counter is thread safe
        System.out.println("Counter value: " + shared.get());
    }
}
