package gurungsijan.com.cleanapp_music.common.executor.implementation;

import android.os.Handler;
import android.os.Looper;

import gurungsijan.com.cleanapp_music.common.executor.interfaces.UIThread;

import javax.inject.Inject;

public class UIThreadImpl implements UIThread {

    protected Handler handler;

    @Inject
    public UIThreadImpl() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}