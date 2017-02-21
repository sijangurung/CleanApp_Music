package gurungsijan.com.cleanapp_music.api.artist;

import gurungsijan.com.cleanapp_music.domain.models.ArtistHolder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public interface ArtistsApiRest {
    //http://api.deezer.com/search/artist?q=prince
    @GET("/search/artist/")
    Call<ArtistHolder> getArtistsByName(@Query("q") String artistName);
}
