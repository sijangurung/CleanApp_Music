package gurungsijan.com.cleanapp_music.common.di.components;

import android.content.Context;

import gurungsijan.com.cleanapp_music.common.di.annontations.PerActivity;
import gurungsijan.com.cleanapp_music.common.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Context activity();
}

