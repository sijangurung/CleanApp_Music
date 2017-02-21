package gurungsijan.com.cleanapp_music.presenter;

import gurungsijan.com.cleanapp_music.common.ui.BaseView;
import gurungsijan.com.cleanapp_music.domain.logic.GetTracks;
import gurungsijan.com.cleanapp_music.domain.models.Track;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sijan Gurung on 12/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class AlbumTrackPresenter implements GetTracks.Callback{
    public interface View extends BaseView {
        void onTracksListResult(List<Track> allTracks);
        void onTracksError(int errorCode);
    }

    GetTracks getTracks;
    View getTrackView;

    @Inject
    public AlbumTrackPresenter(GetTracks getTracks){
        this.getTracks = getTracks;
    }
    public void setGetTrackView(View getTrackView) {
        this.getTrackView = getTrackView;
    }

    public void getTracksForAlbum(String trackURL, View getTrackView){
        setGetTrackView(getTrackView);
        getTrackView.showProgress();
        getTracks.getTrackForAlbum(trackURL, this);
    }

    @Override
    public void notifyAlbumTracks(List<Track> artists) {
        if(getTrackView != null){
            getTrackView.hideProgress();
            getTrackView.onTracksListResult(artists);
        }
    }

    @Override
    public void notifyTrackError(int errorCode) {
        if(getTrackView != null){
            getTrackView.hideProgress();
            getTrackView.onTracksError(errorCode);
        }
    }
}
