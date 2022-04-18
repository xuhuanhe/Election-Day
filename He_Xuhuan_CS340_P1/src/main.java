import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class main {
    public static int num_voters = 20;
    public static int num_ID_checkers = 3;
    public static int num_k = 3;
    public static Voter[]voters;
    public static IdChecker[] IdCheckers;
    public static AtomicBoolean checkId[];

    public static AtomicInteger currentVoters;
    public static AtomicBoolean startCheck;

    public static Queue<Voter> kiosk1 = new LinkedList<>();
    public static Queue<Voter> kiosk2 = new LinkedList<>();
    public static Queue<Voter> kiosk3 = new LinkedList<>();


    public static Kiosk Kiosks[];

    public static AtomicBoolean startToInform;
    public static AtomicInteger currentVoters2;
    public static AtomicBoolean signalInterrup;

    public static AtomicBoolean useScanningMachines[];
    public static int num_sm = 4;

    public static ScanningMachine[] sm;

    public static AtomicInteger currentVoters3;

    public static AtomicBoolean startToScan;
    public static AtomicInteger currentVoters4;
    public static void main(String[] args) {

        Random rand = new Random();
        currentVoters = new AtomicInteger(0);
        currentVoters2 = new AtomicInteger(0);
        voters = new Voter[num_voters];
        IdCheckers = new IdChecker[num_ID_checkers];
        checkId = new AtomicBoolean[num_voters];
        startCheck = new AtomicBoolean(false);
        startToInform = new AtomicBoolean(false);

        useScanningMachines = new AtomicBoolean[num_voters];

        IdCheckers[0] = new IdChecker(1);
        IdCheckers[0].start();
        IdCheckers[1] = new IdChecker(2);
        IdCheckers[1].start();
        IdCheckers[2] = new IdChecker(3);
        IdCheckers[2].start();

        Kiosks = new Kiosk[num_k];
        Kiosks[0] = new Kiosk(1,kiosk1);
        Kiosks[1] = new Kiosk(2,kiosk2);
        Kiosks[2] = new Kiosk(3,kiosk3);

        signalInterrup = new AtomicBoolean(false);
        startToScan = new AtomicBoolean(false);
        currentVoters4 = new AtomicInteger(0);
        for(int i = 0; i < num_voters; i++){
            useScanningMachines[i] = new AtomicBoolean(false);
            useScanningMachines[i] = new AtomicBoolean(false);
            checkId[i] = new AtomicBoolean(false);
            voters[i] = new Voter(i + 1);
            voters[i].start();
            try {
                Thread.sleep(rand.nextInt(500));
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        KioskHelper kioskHelper = new KioskHelper();
        kioskHelper.start();
        scanning_helper smHelper= new scanning_helper();
        smHelper.start();
        currentVoters3 = new AtomicInteger(0);


    }
}
