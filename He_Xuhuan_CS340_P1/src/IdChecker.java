import java.util.Random;

public class IdChecker extends Thread{

    int checkerId;
    Random rand = new Random();
    public IdChecker(int id){
        this.checkerId = id;
        setName("IdChecker-" + id);
    }

    public void run() {
        while(!main.startCheck.get()){};
        for(Voter voter : main.voters){
            if(main.checkId[voter.voterId - 1].get() == false){
                main.checkId[voter.voterId - 1].compareAndSet(false, true);
                msg("checked " + voter.voterId);
                try {
                    Thread.sleep(rand.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


    }
    public static long time = System.currentTimeMillis();

    public void msg(String m) {
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }
}
