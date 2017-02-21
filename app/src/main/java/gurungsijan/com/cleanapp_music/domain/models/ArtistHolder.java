package gurungsijan.com.cleanapp_music.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class ArtistHolder {

    @SerializedName("data")
    List<Artist> allArtists;

    public List<Artist> getAllArtists() {
        return allArtists;
    }

    public void setAllArtists(List<Artist> allArtists) {
        this.allArtists = allArtists;
    }
}
