package gurungsijan.com.cleanapp_music.domain.logic;

import gurungsijan.com.cleanapp_music.domain.models.Track;

import java.util.List;

/**
 * Created by Sijan Gurung on 12/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public interface GetTracks {
    interface Callback {
        void notifyAlbumTracks(List<Track> artists);
        void notifyTrackError(int errorCode);
    }

    void getTrackForAlbum(String trackUrl, Callback callback);
}
