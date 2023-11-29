import java.util.Timer;
import java.util.TimerTask;

public class DisplayTime {
    public Timer timer;

    public void startTimer(int seconds) {
        timerTask = new CustomTimerTask(seconds);
        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

   
    public CustomTimerTask timerTask;
    //This is a nested class that extends TimerTask and is used to define the behavior of the timer.

    static class CustomTimerTask extends TimerTask {
        private int counter;
        
        public CustomTimerTask(int seconds) {
            this.counter = seconds;
        }

        @Override
        public void run() {
            if (counter > 0) {
                counter--;
            }
        }

        public int getCounter() {
            return counter;
        }
       
    }
    
}
