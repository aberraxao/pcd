package ex1_a_2;

public class Counter {
    private int count = 0;

    public int get() {
        return count;
    }

    synchronized public void increment() {
        // count = count + 1;
        // READ count
        // MODIFY add
        // WRITE count
        // synchronized (this) { // could have this here or add it before public
        count++;
        // }
    }

    synchronized public void incrementByValue(int val) {
        // synchronized (this){
        count = count + val;
        //  }
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

