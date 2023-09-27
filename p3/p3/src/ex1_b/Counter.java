package ex1_b;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count = 0;
    private Lock lock = new ReentrantLock(); // Cadeado extrínseco - Lock is an interface

    public int get() {
        return count;
    }

    public void increment() {
        // count = count + 1;
        // READ count
        // MODIFY add
        // WRITE count
        lock.lock();
        try {
            count++;
        } finally { // in case of an issue
            lock.unlock();
        }
    }

    public void incrementByValue(int val) {
        // using the same lock, because we are updating the same variable
        lock.lock();
        try {
            count = count + val;
        } finally { // in case of an issue
            lock.unlock();
        }
    }

    private class ConcurrentCount extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Counter sharedCounter = new Counter(); // Objeto partilhado
        ConcurrentCount[] threads = new ConcurrentCount[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = sharedCounter.new ConcurrentCount(); // Partilha explícita
        }

        // Starts all threads
        for (ConcurrentCount t : threads) {
            t.start();
        }

        // Waits for all threads to end
        for (ConcurrentCount t : threads) {
            t.join();
        }
        // We expect counter <=4000, because we might lose some counts
        System.out.println("Counter value: " + sharedCounter.get());
    }
}

