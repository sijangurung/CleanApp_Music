package gurungsijan.com.cleanapp_music.presenter;

import gurungsijan.com.cleanapp_music.common.ui.BaseView;
import gurungsijan.com.cleanapp_music.domain.logic.GetArtists;
import gurungsijan.com.cleanapp_music.domain.models.Artist;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class ArtistSearchPresenter implements GetArtists.Callback {

    public interface View extends BaseView {
        void onSearchArtistResult(List<Artist> artists);
        void onSearchArtistError(int errorCode);

    }

    GetArtists getArtists;
    View getArtistView;

    @Inject
    public ArtistSearchPresenter(GetArtists getArtists) {
        this.getArtists = getArtists;
    }

    public void setGetArtistView(View getArtistView) {
        this.getArtistView = getArtistView;
    }

    public void searchArtistByName(String artistName, View getArtistView) {
        setGetArtistView(getArtistView);
        if (artistName.trim().length() == 1)
            getArtistView.showProgress();
        getArtists.searchByName(artistName, this);
    }

    @Override
    public void notifyArtists(List<Artist> artists) {
        if (getArtistView != null) {
            getArtistView.hideProgress();
            getArtistView.onSearchArtistResult(artists);
        }
    }

    @Override
    public void notifyArtistGetError(int errorCode) {
        Timber.e("Error on getting artist!");
        if (getArtistView != null) {
            getArtistView.hideProgress();
            getArtistView.onSearchArtistError(errorCode);
        }
    }
}
