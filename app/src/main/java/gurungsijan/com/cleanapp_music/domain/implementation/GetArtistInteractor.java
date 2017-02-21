package gurungsijan.com.cleanapp_music.domain.implementation;

import gurungsijan.com.cleanapp_music.api.artist.ArtistsApiClient;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.Executor;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.Interactor;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.UIThread;
import gurungsijan.com.cleanapp_music.common.network.ExceptionHandler;
import gurungsijan.com.cleanapp_music.domain.logic.GetArtists;
import gurungsijan.com.cleanapp_music.domain.models.Artist;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class GetArtistInteractor implements GetArtists {

    Executor executor;
    UIThread uiThread;
    ArtistsApiClient apiClient;

    @Inject
    public GetArtistInteractor(Executor executor, UIThread uiThread, ArtistsApiClient apiClient) {
        this.executor = executor;
        this.uiThread = uiThread;
        this.apiClient = apiClient;
    }

    @Override
    public void searchByName(final String artistName, final Callback callback) {
        executor.run(new Interactor() {
            @Override
            public void run() {
                try {
                    List<Artist> allArtists = apiClient.getArtistByName(artistName);
                    notifyArtists(callback, allArtists);
                } catch (Exception e) {
                    Timber.e("Got Exception ! "+e);
                    notifyArtistsError(callback, ExceptionHandler.getErrorCode(e));
                }
            }
        });
    }

    private void notifyArtists(final Callback callback, final List<Artist> artists) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                callback.notifyArtists(artists);
            }
        });
    }

    private void notifyArtistsError(final Callback callback, final int errorCode) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Timber.v("ErrorCode: "+errorCode);
                callback.notifyArtistGetError(errorCode);
            }
        });
    }
}
