package gurungsijan.com.cleanapp_music.common.di.modules;

import android.content.Context;

import gurungsijan.com.cleanapp_music.app.DemoMusicApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@Module
public class MainModule {
    private DemoMusicApplication application;

    public MainModule(DemoMusicApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

}