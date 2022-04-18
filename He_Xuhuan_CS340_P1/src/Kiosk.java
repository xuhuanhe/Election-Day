import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Kiosk  extends Thread{

    public AtomicBoolean isAvailable = new AtomicBoolean(true);
    int KioskId;
    Queue<Voter> q = new LinkedList<>();
    public Kiosk(int id, Queue q) {
        this.KioskId = id;
        this.q = q;
        setName("Kiosk-" + id);
    }
}
