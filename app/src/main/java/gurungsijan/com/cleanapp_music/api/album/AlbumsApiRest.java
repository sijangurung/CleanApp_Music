package gurungsijan.com.cleanapp_music.api.album;

import gurungsijan.com.cleanapp_music.domain.models.AlbumHolder;
import gurungsijan.com.cleanapp_music.domain.models.TrackHolder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public interface AlbumsApiRest {
    //http://api.deezer.com/search/album?q=prince
    @GET("/artist/{artist_id}/albums")
    Call<AlbumHolder> getAlbumsByArtist(@Path("artist_id") String artistId);

    @GET
    Call<AlbumHolder> getMoreAlbums(@Url String newUrl);

    @GET
    Call<TrackHolder> getAlbumTracks(@Url String newUrl);

}
