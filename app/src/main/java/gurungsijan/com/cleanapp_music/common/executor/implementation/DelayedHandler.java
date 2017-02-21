package gurungsijan.com.cleanapp_music.common.executor.implementation;

import android.os.Handler;

import gurungsijan.com.cleanapp_music.common.executor.interfaces.Delayed;

import javax.inject.Inject;

public class DelayedHandler implements Delayed {

    private Handler handler;

    @Inject
    public DelayedHandler() {
        handler = new Handler();
    }

    @Override
    public void execute(Runnable runnable, long delay) {
        handler.postDelayed(runnable, delay);
    }

    @Override
    public void cancel() {
        handler.removeCallbacksAndMessages(null);
    }
}