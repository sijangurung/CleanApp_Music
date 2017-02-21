package gurungsijan.com.cleanapp_music.albums.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import gurungsijan.com.cleanapp_music.R;
import gurungsijan.com.cleanapp_music.albums.ui.adapter.AlbumListAdapter;
import gurungsijan.com.cleanapp_music.common.network.ExceptionHandler;
import gurungsijan.com.cleanapp_music.common.ui.BaseActivity;
import gurungsijan.com.cleanapp_music.domain.models.Album;
import gurungsijan.com.cleanapp_music.domain.models.Artist;
import gurungsijan.com.cleanapp_music.presenter.AlbumPresenter;
import timber.log.Timber;

/**
 * Created by Sijan Gurung on 11/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class AlbumActivity extends BaseActivity implements AlbumPresenter.View {

    public static final String ARG_ARTIST_OBJECT = "arg_artist_object";

    AlbumListAdapter adapter = null;
    RecyclerView rvAlbums = null;
    ProgressBar pbAlbumList = null;

    Artist thisArtist = null;

    @Inject
    AlbumPresenter albumPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);

        Intent thisIntent = getIntent();
        if (thisIntent.getExtras() != null) {
            String strArtist = thisIntent.getStringExtra(ARG_ARTIST_OBJECT);
            thisArtist = new Gson().fromJson(strArtist, Artist.class);
        }

        View rootView = getLayoutInflater().inflate(R.layout.activity_album, container);
        rvAlbums = (RecyclerView) rootView.findViewById(R.id.rvAllAlbums);
        pbAlbumList = (ProgressBar) rootView.findViewById(R.id.pbAlbumList);

        setupRecyclerView();

        final View toolbarView = loadToolbarRes(R.layout.toolbar_album);
        final TextView txtTBArtist = (TextView) toolbarView.findViewById(R.id.txtTBTopTitle);
        final ImageView imgSeachView = (ImageView) toolbarView.findViewById(R.id.imgTBSearchArtist);
        imgSeachView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (thisArtist != null) {
            txtTBArtist.setText(thisArtist.getName());
            albumPresenter.searchAlbumByArtistID(thisArtist.getId().toString(), this);
        }
    }

    protected void setupRecyclerView() {

        rvAlbums.setLayoutManager(new GridLayoutManager(this, 2));
        rvAlbums.setNestedScrollingEnabled(false);

        adapter = new AlbumListAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Album myAlbum = (Album) view.getTag();
                Intent albumDetailIntent = new Intent(AlbumActivity.this, gurungsijan.com.cleanapp_music.albums.ui.activity.AlbumDetailActivity.class);
                albumDetailIntent.putExtra(gurungsijan.com.cleanapp_music.albums.ui.activity.AlbumDetailActivity.ARG_ALBUM_TRACK_URL, myAlbum.getTracklist());
                albumDetailIntent.putExtra(gurungsijan.com.cleanapp_music.albums.ui.activity.AlbumDetailActivity.ARG_ALBUM_COVER_BIG, myAlbum.getCoverMedium());
                albumDetailIntent.putExtra(gurungsijan.com.cleanapp_music.albums.ui.activity.AlbumDetailActivity.ARG_ARTIST_NAME, thisArtist.getName());
                albumDetailIntent.putExtra(gurungsijan.com.cleanapp_music.albums.ui.activity.AlbumDetailActivity.ARG_ALBUM_TITLE, myAlbum.getTitle());

                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(AlbumActivity.this,
                                (View) view.findViewById(R.id.imgAlbumCover),
                                getString(R.string.albumCover_T));

                startActivityForResult(albumDetailIntent, 100, options.toBundle());
            }
        });
        rvAlbums.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                //send search to the main activity,,,
                /*IF USED FRAGMENTS, THEN this can be handled from Single Activity... */
                finish();
            }
        }
    }

    @Override
    public void onAlbumResult(List<Album> albums) {
        Timber.i("Got the albums.. updating the list!!!");
        if (adapter != null) {
            adapter.updateList(albums);
        }
    }

    @Override
    public void onAlbumError(int errorCode) {
        //Show dialog .. but for now show toast
        Timber.e("Error with code: " + errorCode);
        String strErrorMessage = ExceptionHandler.getErrorString(errorCode, getResources());
        Toast.makeText(context, strErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        pbAlbumList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbAlbumList.setVisibility(View.GONE);
    }
}
