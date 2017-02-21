package gurungsijan.com.cleanapp_music.common.di.modules;

import android.content.Context;

import gurungsijan.com.cleanapp_music.common.ui.BaseActivity;
import gurungsijan.com.cleanapp_music.common.di.annontations.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@Module
public class ActivityModule {
    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Context provideActivityContext() {
        return activity;
    }
}

