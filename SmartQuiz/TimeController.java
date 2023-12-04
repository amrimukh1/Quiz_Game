package SmartQuiz;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.time.Duration;

public class TimeController {
    public Timer timer;
    public long total_time;

    private Instant startTime;

    public CustomTimerTask timerTask;

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

    public double calculatePoints(double timeRemaining) {
        return timeRemaining;
    }

    public void recordStartTime() {
        startTime = Instant.now();
    }

    public MyTimeDataClass recordEndTime() {
        return new MyTimeDataClass(Duration.between(startTime, Instant.now()).getSeconds());
    }

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
