package gurungsijan.com.cleanapp_music.domain.implementation;

import gurungsijan.com.cleanapp_music.api.album.AlbumsApiClient;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.Executor;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.Interactor;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.UIThread;
import gurungsijan.com.cleanapp_music.common.network.ExceptionHandler;
import gurungsijan.com.cleanapp_music.domain.logic.GetAlbums;
import gurungsijan.com.cleanapp_music.domain.models.Album;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class GetAlbumInteractor implements GetAlbums {

    Executor executor;
    UIThread uiThread;
    AlbumsApiClient apiClient;

    @Inject
    public GetAlbumInteractor(Executor executor, UIThread uiThread, AlbumsApiClient apiClient) {
        this.executor = executor;
        this.uiThread = uiThread;
        this.apiClient = apiClient;
    }

    private void notifyAlbumsFetch(final Callback callback, final List<Album> albums) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                callback.notifyAlbums(albums);
            }
        });
    }

    private void notifyAlbumsFetchError(final Callback callback, final int errorCode) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Timber.v("ErrorCode: " + errorCode);
                callback.notifyAlbumGetError(errorCode);
            }
        });
    }

    @Override
    public void searchAlbumByArtistID(final String artistID, final Callback callback) {
        executor.run(new Interactor() {
            @Override
            public void run() {
                try {
                    List<Album> allAlbums = apiClient.getAlbumsByArtist(artistID);
                    notifyAlbumsFetch(callback, allAlbums);
                } catch (Exception e) {
                    Timber.e("Got Exception ! " + e);
                    notifyAlbumsFetchError(callback, ExceptionHandler.getErrorCode(e));
                }
            }
        });
    }
}
