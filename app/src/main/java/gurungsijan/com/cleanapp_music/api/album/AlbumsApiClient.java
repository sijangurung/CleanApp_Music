package gurungsijan.com.cleanapp_music.api.album;

import gurungsijan.com.cleanapp_music.common.network.NetworkClient;
import gurungsijan.com.cleanapp_music.common.network.NetworkConfig;
import gurungsijan.com.cleanapp_music.domain.models.Album;
import gurungsijan.com.cleanapp_music.domain.models.AlbumHolder;
import gurungsijan.com.cleanapp_music.domain.models.Track;
import gurungsijan.com.cleanapp_music.domain.models.TrackHolder;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import timber.log.Timber;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class AlbumsApiClient extends NetworkClient {

    AlbumsApiRest api;
    Call<TrackHolder> call = null;
    List<Track> allTracks = Collections.emptyList();

    public AlbumsApiClient(NetworkConfig config) {
        super(config);
        this.api = getApi(AlbumsApiRest.class);
    }

    public List<Album> getAlbumsByArtist(String artistID) throws Exception {
        Call<AlbumHolder> call = null;
        List<Album> allAlbums = Collections.emptyList();
        try {
            call = api.getAlbumsByArtist(artistID);
            AlbumHolder holder = execute(call);
            allAlbums = holder.getAllAlbums();
            if (holder.getNext() != null) {
                call = api.getMoreAlbums(holder.getNext());
                AlbumHolder holder_ = execute(call);
                allAlbums.addAll(holder_.getAllAlbums());
            }
        } catch (Exception e) {
            Timber.e("Exception occurred in client! ");
            throw e;
        }
        return allAlbums;
    }

    public List<Track> getTracksForAlbum(String trackURL) throws Exception {
        try {
            call = api.getAlbumTracks(trackURL);
            TrackHolder holder = execute(call);
            allTracks = holder.getTracks();
            if (holder.getNext() != null) {
                allTracks.addAll(getTracksForAlbum(holder.getNext()));
            }
            Timber.v("Total tracks are : "+holder.getTotal());
        } catch (Exception e) {
            Timber.e("Exception occurred in client! ");
            throw e;
        }
        return allTracks;
    }
}
