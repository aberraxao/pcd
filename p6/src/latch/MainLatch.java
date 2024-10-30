package latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class MainLatch {
    static final int NUM_DOCUMENTS_TO_BE_SEARCHED = 1000;
    static final int STRING_LENGTH = 1024;
    static final String STRING_TO_BE_FOUND = "huik";

    public static void main(String[] args) throws InterruptedException { // throw pq estou no main
        // Solution using a latch
        // 1) Create a CountDownLatch and share it all threads
        // 2) SearchThread: Doe its work and decrease the latch (countDown() method)
        // 3) Choose a thread to wait: Thread main will wait the latch and summarize the results

        final long initTime = System.currentTimeMillis();
        SearcherThread[] threads = new SearcherThread[NUM_DOCUMENTS_TO_BE_SEARCHED];
        CountDownLatch latch = new CountDownLatch(NUM_DOCUMENTS_TO_BE_SEARCHED);

        RandomString rs = new RandomString(STRING_LENGTH);
        for (int i = 0; i != NUM_DOCUMENTS_TO_BE_SEARCHED; i++) {
            threads[i] = new SearcherThread(rs.nextString(), STRING_TO_BE_FOUND, latch);
            threads[i].start();
        }

        // 3
        latch.await();

        int count = 0;
        for (SearcherThread t : threads)
            if (t.getResult() != -1) {
                System.out.println("Found at " + t.getResult());//+" in "+t.getMyText());
                count++;
            }
        // O tempo de espera vem no tempo da criação das threads
        System.out.println("Search DONE. Found:" + count + " Time:" + (System.currentTimeMillis() - initTime));
    }
}
