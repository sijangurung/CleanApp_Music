package gurungsijan.com.cleanapp_music.domain.implementation;

import gurungsijan.com.cleanapp_music.api.album.AlbumsApiClient;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.Executor;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.Interactor;
import gurungsijan.com.cleanapp_music.common.executor.interfaces.UIThread;
import gurungsijan.com.cleanapp_music.common.network.ExceptionHandler;
import gurungsijan.com.cleanapp_music.domain.logic.GetTracks;
import gurungsijan.com.cleanapp_music.domain.models.Track;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Sijan Gurung on 12/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class GetTrackInteractor implements GetTracks {

    Executor executor;
    UIThread uiThread;
    AlbumsApiClient apiClient;

    @Inject
    public GetTrackInteractor(Executor executor, UIThread uiThread, AlbumsApiClient apiClient) {
        this.executor = executor;
        this.uiThread = uiThread;
        this.apiClient = apiClient;
    }

    @Override
    public void getTrackForAlbum(final String trackUrl, final Callback callback) {
        executor.run(new Interactor() {
            @Override
            public void run() {
                try {
                    List<Track> allTracks = apiClient.getTracksForAlbum(trackUrl);
                    notifyTracksFetch(callback, allTracks);
                } catch (Exception e) {
                    Timber.e("Got Exception ! " + e);
                    notifyTracksFetchError(callback, ExceptionHandler.getErrorCode(e));
                }
            }
        });
    }

    private void notifyTracksFetch(final Callback callback, final List<Track> tracks) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                callback.notifyAlbumTracks(tracks);
            }
        });
    }

    private void notifyTracksFetchError(final Callback callback, final int errorCode) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Timber.v("ErrorCode: " + errorCode);
                callback.notifyTrackError(errorCode);
            }
        });
    }
}
