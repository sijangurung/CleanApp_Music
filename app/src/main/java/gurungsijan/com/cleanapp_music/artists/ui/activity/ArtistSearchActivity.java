package gurungsijan.com.cleanapp_music.artists.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import gurungsijan.com.cleanapp_music.R;
import gurungsijan.com.cleanapp_music.albums.ui.activity.AlbumActivity;
import gurungsijan.com.cleanapp_music.artists.ui.adapter.ArtistListAdapter;
import gurungsijan.com.cleanapp_music.common.network.ExceptionHandler;
import gurungsijan.com.cleanapp_music.common.ui.BaseActivity;
import gurungsijan.com.cleanapp_music.common.ui.SimpleDividerItemDecoration;
import gurungsijan.com.cleanapp_music.domain.models.Artist;
import gurungsijan.com.cleanapp_music.presenter.ArtistSearchPresenter;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class ArtistSearchActivity extends BaseActivity implements ArtistSearchPresenter.View {

    ArtistListAdapter adapter = null;
    RecyclerView rvArtist = null;
    ProgressBar pbArtistList = null;

    @Inject
    ArtistSearchPresenter artistSearchPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);

        View rootView = getLayoutInflater().inflate(R.layout.activity_artist_search, container);
        rvArtist = (RecyclerView) rootView.findViewById(R.id.rvAllArtists);
        pbArtistList = (ProgressBar) rootView.findViewById(R.id.pbArtistList);

        setupRecyclerview();

        final View toolbarView = loadToolbarRes(R.layout.toolbar_artistsearch);
        SearchView artistSearchView = (SearchView) toolbarView.findViewById(R.id.svArtistSearch);

        artistSearchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //searching or filtering ...
                        artistSearchPresenter.searchArtistByName(newText, ArtistSearchActivity.this);
                        return false;
                    }
                }
        );
    }

    protected void setupRecyclerview() {
        rvArtist.setLayoutManager(new LinearLayoutManager(this));
        rvArtist.setNestedScrollingEnabled(false);
        rvArtist.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));

        adapter = new ArtistListAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Artist myArtist = (Artist) view.getTag();
                Intent albumIntent = new Intent(ArtistSearchActivity.this, AlbumActivity.class);
                albumIntent.putExtra(AlbumActivity.ARG_ARTIST_OBJECT, new Gson().toJson(myArtist));
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(ArtistSearchActivity.this,
                                (View) view.findViewById(R.id.txtArtistName),
                                getString(R.string.artistName_T));
                startActivity(albumIntent,options.toBundle());
            }
        });

        rvArtist.setAdapter(adapter);
    }

    @Override
    public void onSearchArtistResult(List<Artist> artists) {
        Timber.v("Got the Artists , updating the recyclerview!");
        if (adapter != null)
            adapter.updateList(artists);
    }

    @Override
    public void onSearchArtistError(int errorCode) {
        //Show dialog .. but for now show toast
        Timber.e("Error with code: " + errorCode);
        String strErrorMessage = ExceptionHandler.getErrorString(errorCode, getResources());
        Toast.makeText(context, strErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        pbArtistList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbArtistList.setVisibility(View.GONE);
    }
}
