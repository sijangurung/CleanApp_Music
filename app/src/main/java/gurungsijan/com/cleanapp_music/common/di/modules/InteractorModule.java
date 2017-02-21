package gurungsijan.com.cleanapp_music.common.di.modules;

import gurungsijan.com.cleanapp_music.domain.implementation.GetAlbumInteractor;
import gurungsijan.com.cleanapp_music.domain.implementation.GetArtistInteractor;
import gurungsijan.com.cleanapp_music.domain.implementation.GetTrackInteractor;
import gurungsijan.com.cleanapp_music.domain.logic.GetAlbums;
import gurungsijan.com.cleanapp_music.domain.logic.GetArtists;
import gurungsijan.com.cleanapp_music.domain.logic.GetTracks;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@Module
public class InteractorModule {

    @Provides
    @Singleton
    public GetArtists provideGetArtistsInteractor(GetArtistInteractor interactor) {
        return interactor;
    }

    @Provides
    @Singleton
    public GetAlbums provideGetAlbumsInteractor(GetAlbumInteractor interactor) {
        return interactor;
    }

    @Provides
    @Singleton
    public GetTracks provideGetAlbumsTracksInteractor(GetTrackInteractor interactor) {
        return interactor;
    }
}
