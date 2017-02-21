package gurungsijan.com.cleanapp_music.common.di.modules;

import gurungsijan.com.cleanapp_music.api.album.AlbumsApiClient;
import gurungsijan.com.cleanapp_music.api.artist.ArtistsApiClient;
import gurungsijan.com.cleanapp_music.common.network.NetworkConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    ArtistsApiClient provideArtistApiClient() {
        NetworkConfig config = new NetworkConfig.Builder().build();
        return new ArtistsApiClient(config);
    }

    @Provides
    @Singleton
    AlbumsApiClient provideAlbumApiClient() {
        NetworkConfig config = new NetworkConfig.Builder().build();
        return new AlbumsApiClient(config);
    }
}
