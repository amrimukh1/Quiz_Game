package SmartQuiz;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;
import java.time.Duration;

public class TimeController {
    public Timer timer;
    public long total_time;

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

    public Instant recordStartTime() {

        Instant startTime = Instant.now();
        return startTime;

    }

    public long recordEndTime() {
        Instant endTime = Instant.now();
        Duration duration = Duration.between(recordStartTime(), endTime);
        total_time = duration.getSeconds();

        // convert the seconds into minutes divided by 60
        long minutes = total_time / 60;
        return minutes;
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
