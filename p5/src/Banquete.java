import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banquete {

    ArrayList<Javali> mesa;
    private int maxSize;
    private Lock lock = new ReentrantLock();
    private Condition fullTable = lock.newCondition();
    private Condition emptyTable = lock.newCondition();

    public Banquete(int maxSize) {
        mesa = new ArrayList<Javali>();
        this.maxSize = maxSize;
    }

    public void colocaJavali(Javali javali) throws InterruptedException {
        // Vantagem: cada banquete tem o seu cadeado
        lock.lock();
        try {
            while (mesa.size() >= maxSize) {
                fullTable.await();
            }
            mesa.add(javali);
            emptyTable.signalAll(); // Só vou acordar quem estava a dormir
        } finally {
            // finally: Para garantir que liberto sempre um cadeado
            lock.unlock();
        }
    }

    public Javali devolveJavali() throws InterruptedException {
        lock.lock();
        try {
            while (mesa.size() == 0) {
                emptyTable.await();
            }
            Javali javaliCozinhado = mesa.get(0);
            mesa.remove(0);
            fullTable.signalAll();
            return javaliCozinhado;
        } finally {
            lock.unlock();
        }
    }
}
