import java.util.concurrent.atomic.AtomicBoolean;

public class ScanningMachine {
    public AtomicBoolean isAvailable = new AtomicBoolean(true);
    int smId;
    public ScanningMachine(int id){
        this.smId = id;
    }
}
