import java.util.concurrent.atomic.AtomicInteger;

public class scanning_helper extends Thread{
    public void run(){
        while(!main.startToScan.get()){}
        while(main.currentVoters3.get() <= main.num_voters){
            int count = 0;
            for(Voter voters: main.voters){
                if(main.useScanningMachines[voters.voterId - 1].get() == false){
                    main.useScanningMachines[voters.voterId - 1].set(true);
                    count++;
                    if(count % 4 == 0){
                        try {
                            voters.msg("people in the same room has jointed " + voters.getName());
                            voters.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                main.currentVoters3.incrementAndGet();
            }
        }
    }

}
