package SmartQuiz;

public class MyTimeDataClass {
    private long minutes;
    private long seconds;

    public long getMinutes() {
        return minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public MyTimeDataClass(long seconds) {
        this.minutes = seconds / 60;
        this.seconds = seconds % 60;
    }
}
