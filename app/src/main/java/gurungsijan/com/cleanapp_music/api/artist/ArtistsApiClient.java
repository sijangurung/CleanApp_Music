package gurungsijan.com.cleanapp_music.api.artist;

import gurungsijan.com.cleanapp_music.common.network.NetworkClient;
import gurungsijan.com.cleanapp_music.common.network.NetworkConfig;
import gurungsijan.com.cleanapp_music.domain.models.Artist;
import gurungsijan.com.cleanapp_music.domain.models.ArtistHolder;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import timber.log.Timber;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class ArtistsApiClient extends NetworkClient {

    ArtistsApiRest api;

    public ArtistsApiClient(NetworkConfig config) {
        super(config);
        this.api = getApi(ArtistsApiRest.class);
    }

    public List<Artist> getArtistByName(String artistName) throws Exception{
        Call<ArtistHolder> call  = null;
        List<Artist> allArtists = Collections.emptyList();
        try {
            call  = api.getArtistsByName(artistName);
            ArtistHolder holder = execute(call);
            allArtists = holder.getAllArtists();

        } catch (Exception e) {
            Timber.e("Exception occurred in client! ");
            throw e;
        }
        return allArtists;
    }

}