package gurungsijan.com.cleanapp_music.common.di.components;

import gurungsijan.com.cleanapp_music.albums.ui.activity.AlbumActivity;
import gurungsijan.com.cleanapp_music.albums.ui.activity.AlbumDetailActivity;
import gurungsijan.com.cleanapp_music.app.DemoMusicApplication;
import gurungsijan.com.cleanapp_music.artists.ui.activity.ArtistSearchActivity;
import gurungsijan.com.cleanapp_music.common.di.modules.DataModule;
import gurungsijan.com.cleanapp_music.common.di.modules.ExecutorModule;
import gurungsijan.com.cleanapp_music.common.di.modules.InteractorModule;
import gurungsijan.com.cleanapp_music.common.di.modules.MainModule;
import gurungsijan.com.cleanapp_music.common.di.modules.NetworkModule;
import gurungsijan.com.cleanapp_music.common.di.modules.PresenterModule;
import gurungsijan.com.cleanapp_music.common.ui.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@Singleton
@Component(modules = {
        MainModule.class,
        ExecutorModule.class,
        PresenterModule.class,
        InteractorModule.class,
        DataModule.class,
        NetworkModule.class
}
)
public interface AppComponent {
    void inject(DemoMusicApplication application);
    void inject(BaseActivity activity);
    void inject(ArtistSearchActivity activity);
    void inject(AlbumActivity activity);
    void inject(AlbumDetailActivity activity);

}