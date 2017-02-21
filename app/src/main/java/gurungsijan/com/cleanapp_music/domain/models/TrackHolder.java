package gurungsijan.com.cleanapp_music.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class TrackHolder {

    @SerializedName("data")
    List<Track> tracks;

    @SerializedName("total")
    int total;

    @SerializedName("prev")
    String prev;

    @SerializedName("next")
    String next;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
