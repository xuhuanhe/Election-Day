import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Voter extends Thread{
    Random rand = new Random();
    int voterId;

    public Voter(int id) {
        this.voterId = id;
        setName("Voter-" + id);
    }

    public void run() {

        msg("arrived at the designated voting place");
        main.currentVoters.incrementAndGet();
        if(main.currentVoters.get() == 20){
            main.startCheck.set(true);
        }
        while(!main.checkId[voterId- 1].get()){
        };
        msg("enter the voting kiosk line.");

        AtomicInteger temp = new AtomicInteger(0);
        if(main.kiosk1.size() <= main.kiosk2.size() && main.kiosk1.size() <= main.kiosk3.size()){
            temp.set(1);
        }else if (main.kiosk2.size() <= main.kiosk1.size() && main.kiosk2.size() <= main.kiosk3.size()){
            temp.set(2);
        }else if (main.kiosk3.size() <= main.kiosk1.size() && main.kiosk1.size() <= main.kiosk2.size()){
            temp.set(3);
        }

        if(temp.get() == 1){
            main.kiosk1.add(this);
            msg("pick kiosk 1");
        }else if(temp.get() == 2){
            main.kiosk2.add(this);
            msg("pick kiosk 2");
        }else{
            main.kiosk3.add(this);
            msg("pick kiosk 3");
        }

        main.currentVoters2.incrementAndGet();
        if(main.currentVoters2.get() == 20){
            main.startToInform.set(true);
        }

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            msg(e.getMessage());
        }

        for(Kiosk k: main.Kiosks){
            if(k.q.contains(this)){
                k.isAvailable.set(false);
            }
        }
        msg("done completing ballot ");

        for(Kiosk k: main.Kiosks){
            if(k.q.contains(this)){
                k.q.poll();
                k.isAvailable.set(true);
            }
        }

        msg("rush to the room with scanning machines");
        int p = this.getPriority();
        this.setPriority(MAX_PRIORITY);
        try {
            Thread.sleep(rand.nextInt(5000));
        } catch (InterruptedException e) {
            msg(e.getMessage());
        }
        this.setPriority(p);


        main.currentVoters4.incrementAndGet();
        if(main.currentVoters4.get() == 20){
            main.startToScan.set(true);
        }



        while(!main.useScanningMachines[voterId - 1].get()){
        }
        this.yield();
        this.yield();
        msg("scanning ballot");
        try {
            Thread.sleep(rand.nextInt(5000));
        } catch (InterruptedException e) {
            msg(e.getMessage());
        }


    }




    public static long time = System.currentTimeMillis();

    public void msg(String m) {
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }
}

