package gurungsijan.com.cleanapp_music.domain.logic;

import gurungsijan.com.cleanapp_music.domain.models.Album;

import java.util.List;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public interface GetAlbums {

    interface Callback {
        void notifyAlbums(List<Album> albums);
        void notifyAlbumGetError(int errorCode);
    }

    void searchAlbumByArtistID(String artistID, Callback callback);
}
