package gurungsijan.com.cleanapp_music.albums.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import gurungsijan.com.cleanapp_music.R;
import gurungsijan.com.cleanapp_music.albums.ui.adapter.TrackListAdapter;
import gurungsijan.com.cleanapp_music.common.network.ExceptionHandler;
import gurungsijan.com.cleanapp_music.common.ui.BaseActivity;
import gurungsijan.com.cleanapp_music.common.ui.SimpleDividerItemDecoration;
import gurungsijan.com.cleanapp_music.domain.models.Track;
import gurungsijan.com.cleanapp_music.presenter.AlbumTrackPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Sijan Gurung on 12/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class AlbumDetailActivity extends BaseActivity implements AlbumTrackPresenter.View {

    public static final String ARG_ALBUM_TRACK_URL = "arg_album_track_url";
    public static final String ARG_ALBUM_COVER_BIG = "arg_album_cover_big";
    public static final String ARG_ARTIST_NAME = "arg_artist_name";
    public static final String ARG_ALBUM_TITLE = "arg_album_title";

    //widgets
    ImageView imgAlbumCover;
    ProgressBar pbTrackList;
    RecyclerView rvTrackList;

    MediaPlayer mediaPlayer;
    private int playbackPosition = 0;

    //adapter
    TrackListAdapter adapter = null;
    //data
    String imgAlbumCoverUrl = "https://placeholdit.imgix.net/~text?txtsize=33&txt=AlbumCover&w=350&h=350";
    String albumTrackUrl = "";
    String albumTitleText = "";
    String albumArtistText = "";

    @Inject
    AlbumTrackPresenter albumTrackPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);

        Intent thisIntent = getIntent();
        if (thisIntent.getExtras() != null) {
            imgAlbumCoverUrl = thisIntent.getStringExtra(ARG_ALBUM_COVER_BIG);
            albumTrackUrl = thisIntent.getStringExtra(ARG_ALBUM_TRACK_URL);
            albumTitleText = thisIntent.getStringExtra(ARG_ALBUM_TITLE);
            albumArtistText = thisIntent.getStringExtra(ARG_ARTIST_NAME);
        }

        View rootView = getLayoutInflater().inflate(R.layout.activity_tracks, container);
        imgAlbumCover = (ImageView) rootView.findViewById(R.id.imgAlbumCover_track);
        rvTrackList = (RecyclerView) rootView.findViewById(R.id.rvTrackList);
        pbTrackList = (ProgressBar) rootView.findViewById(R.id.pbTrackList);

        setupRecyclerview();

        final View toolbarView = loadToolbarRes(R.layout.toolbar_album);
        final TextView txtTBAlbumTitle = (TextView) toolbarView.findViewById(R.id.txtTBTopTitle);
        final TextView txtTBArtistName = (TextView) toolbarView.findViewById(R.id.txtTBBottomTitle);
        final ImageView imgSeachView = (ImageView) toolbarView.findViewById(R.id.imgTBSearchArtist);
        imgSeachView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
        txtTBAlbumTitle.setText(albumTitleText);
        txtTBArtistName.setText(albumArtistText);

        Picasso.with(context).load(imgAlbumCoverUrl).into(imgAlbumCover);

        if (albumTrackUrl.trim().length() > 1) {
            albumTrackPresenter.getTracksForAlbum(albumTrackUrl, this);
        }
    }

    protected void setupRecyclerview() {
        rvTrackList.setLayoutManager(new LinearLayoutManager(this));
        rvTrackList.setNestedScrollingEnabled(false);
        rvTrackList.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));

        adapter = new TrackListAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Track thisTrack = (Track) view.getTag();
                try {
                    Toast.makeText(context, "Playing preview : " + thisTrack.getTitle(), Toast.LENGTH_SHORT).show();
                    playAudio(thisTrack.getPreview());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        rvTrackList.setAdapter(adapter);
    }

    @Override
    public void onTracksListResult(List<Track> allTracks) {
        Timber.v("All tracks received..showing it in list...");
        if (adapter != null) {
            adapter.updateList(allTracks);
        }
    }

    @Override
    public void onTracksError(int errorCode) {
        //Show dialog .. but for now show toast
        Timber.e("Error with code: " + errorCode);
        String strErrorMessage = ExceptionHandler.getErrorString(errorCode, getResources());
        Toast.makeText(context, strErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        pbTrackList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbTrackList.setVisibility(View.GONE);
    }

    /*DEMO Media player */

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
