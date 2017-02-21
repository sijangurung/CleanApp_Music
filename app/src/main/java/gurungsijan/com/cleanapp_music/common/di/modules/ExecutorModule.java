package gurungsijan.com.cleanapp_music.common.di.modules;

import gurungsijan.com.cleanapp_music.common.executor.implementation.DelayedHandler;
import gurungsijan.com.cleanapp_music.common.executor.implementation.ThreadExecutor;
import gurungsijan.com.cleanapp_music.common.executor.implementation.UIThreadImpl;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.Delayed;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.Executor;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@Module
public class ExecutorModule {

    @Provides
    @Singleton
    public Executor provideExecutor(ThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides
    @Singleton
    public UIThread provideMainThread(UIThreadImpl mainThread) {
        return mainThread;
    }

    @Provides
    public Delayed provideDelayed(DelayedHandler delayedHandler){
        return delayedHandler;
    }
}
