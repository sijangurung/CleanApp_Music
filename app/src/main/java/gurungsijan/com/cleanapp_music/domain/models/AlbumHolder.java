package gurungsijan.com.cleanapp_music.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class AlbumHolder {

    @SerializedName("data")
    List<Album> allAlbums;

    @SerializedName("total")
    int total;

    @SerializedName("prev")
    String prev;

    @SerializedName("next")
    String next;

    public List<Album> getAllAlbums() {
        return allAlbums;
    }

    public void setAllAlbums(List<Album> allAlbums) {
        this.allAlbums = allAlbums;
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
