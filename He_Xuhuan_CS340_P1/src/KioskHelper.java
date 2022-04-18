import java.util.Random;

public class KioskHelper extends Thread{
    Random rand = new Random();
    public void run(){
        while(!main.startToInform.get()){
        }

        while(!main.Kiosks[0].q.isEmpty() || !main.Kiosks[1].q.isEmpty() || !main.Kiosks[2].q.isEmpty()){
            for(Kiosk Kiosks: main.Kiosks){
                if(Kiosks.isAvailable.get()){
                    try {
                        Thread.sleep(rand.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Kiosks.q.peek() != null) Kiosks.q.peek().interrupt();
                }
            }

        }
    }

}
