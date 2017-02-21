package gurungsijan.com.cleanapp_music.common.di.modules;

import gurungsijan.com.cleanapp_music.domain.logic.GetAlbums;
import gurungsijan.com.cleanapp_music.domain.logic.GetArtists;
import gurungsijan.com.cleanapp_music.domain.logic.GetTracks;
import gurungsijan.com.cleanapp_music.presenter.AlbumPresenter;
import gurungsijan.com.cleanapp_music.presenter.AlbumTrackPresenter;
import gurungsijan.com.cleanapp_music.presenter.ArtistSearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@Module
public class PresenterModule {

    @Provides
    ArtistSearchPresenter provideSearchClubsPresenter(GetArtists getArtists) {
        return new ArtistSearchPresenter(getArtists);
    }

    @Provides
    AlbumPresenter provideAlbumPresenter(GetAlbums getAlbums) {
        return new AlbumPresenter(getAlbums);
    }

    @Provides
    AlbumTrackPresenter provideAlbumTrackPresenter(GetTracks getTracks) {
        return new AlbumTrackPresenter(getTracks);
    }


}
