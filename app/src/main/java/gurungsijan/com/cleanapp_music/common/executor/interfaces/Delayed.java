package gurungsijan.com.cleanapp_music.common.executor.interfaces;

public interface Delayed {
    void execute(Runnable runnable, long delay);
    void cancel();
}