package ex1_a;

public class Counter {
    private int count = 0;

    public int get() {
        return count;
    }

    public void increment() {
        // count = count + 1;
        // READ count
        // MODIFY add
        // WRITE count
        count++;
    }

    /*
        private static class ConcurrentCount extends Thread {
       private Counter shareadCounter;
       public ConcurrentCount(Counter sharedCounter){
           this.shareadCounter = sharedCounter;
       }

        @Override
        public void run(){
            // TODO: 1000 incrementos no contador
        }

    }
     */

    private class ConcurrentCount extends Thread {

        @Override
        public void run() {
            // TODO: 1000 incrementos no contador
            // Acesso ao contador "ex1_b.Counter.this"

            for (int i = 0; i < 1000; i++) {
                increment(); // same as ex1_b.Counter.this.increment();
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {
        /*
        Counter sharedCounter = new Counter(); // Objeto partilhado
        Counter.ConcurrentCount[] threads = new Counter.ConcurrentCount[4];
        for (int i=0; i< threads.length; i++){
            threads[i] = new Counter.ConcurrentCount(sharedCounter); // Partilha explícita
        }
        */

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
        System.out.println("ex1_b.Counter value: " + sharedCounter.get());
    }

}

