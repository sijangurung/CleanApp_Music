package gurungsijan.com.cleanapp_music.domain.logic;

import gurungsijan.com.cleanapp_music.domain.models.Artist;

import java.util.List;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public interface GetArtists {

    interface Callback{
        void notifyArtists(List<Artist> artists);
        void notifyArtistGetError(int errorCode);
    }
    void searchByName(String artistName, Callback callback);
}
