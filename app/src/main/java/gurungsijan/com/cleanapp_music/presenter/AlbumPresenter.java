package gurungsijan.com.cleanapp_music.presenter;

import gurungsijan.com.cleanapp_music.common.ui.BaseView;
import gurungsijan.com.cleanapp_music.domain.logic.GetAlbums;
import gurungsijan.com.cleanapp_music.domain.models.Album;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class AlbumPresenter implements GetAlbums.Callback {
    public interface View extends BaseView {
        void onAlbumResult(List<Album> albums);
        void onAlbumError(int errorCode);
    }

    GetAlbums getAlbums;
    View getAlbumView;

    @Inject
    public AlbumPresenter(GetAlbums getAlbums){
        this.getAlbums = getAlbums;
    }


    public void setGetAlbumView(View getAlbumView) {
        this.getAlbumView = getAlbumView;
    }

    public void searchAlbumByArtistID(String artistID, View getAlbumView) {
        setGetAlbumView(getAlbumView);
        getAlbumView.showProgress();
        getAlbums.searchAlbumByArtistID(artistID, this);
    }

    @Override
    public void notifyAlbums(List<Album> albums) {
        if (getAlbumView != null) {
            getAlbumView.hideProgress();
            getAlbumView.onAlbumResult(albums);
        }
    }

    @Override
    public void notifyAlbumGetError(int errorCode) {
        if (getAlbumView != null) {
            getAlbumView.hideProgress();
            getAlbumView.onAlbumError(errorCode);
        }
    }
}
