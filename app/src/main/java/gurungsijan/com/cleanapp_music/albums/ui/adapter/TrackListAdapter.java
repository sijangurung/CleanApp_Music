package gurungsijan.com.cleanapp_music.albums.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gurungsijan.com.cleanapp_music.R;
import gurungsijan.com.cleanapp_music.common.utils.Converter;
import gurungsijan.com.cleanapp_music.domain.models.Track;

import java.util.List;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.TrackViewHolder> {

    List<Track> list;
    private View.OnClickListener itemClickListener;

    public TrackListAdapter(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void updateList(List<Track> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        boolean showHeader = false;
        String headerText = "Volume ";
        Track thisItem = list.get(position);
        if(thisItem.getTrackPosition() == 1 || position == 0)
            showHeader = true;

        holder.bind(list.get(position), itemClickListener,showHeader, headerText);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class TrackViewHolder extends RecyclerView.ViewHolder {

        private View view;
        TextView txtTrackName, txtTrackArtist, txtTrackNumber, txtTrackLength, txtAlbumHeaderText;

        TrackViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            txtAlbumHeaderText = (TextView) itemView.findViewById(R.id.txtAlbumHeaderText);
            txtTrackNumber = (TextView) itemView.findViewById(R.id.txtAlbumTrackNo);
            txtTrackName = (TextView) itemView.findViewById(R.id.txtAlbumTrackName);
            txtTrackArtist = (TextView) itemView.findViewById(R.id.txtAlbumTrackArtist);
            txtTrackLength = (TextView) itemView.findViewById(R.id.txtAlbumTrackLength);
        }

        void bind(Track track, View.OnClickListener itemClickListener, boolean showHeader, String headerText) {
           // txtTrackNumber.setText(track.getTrackPosition());
            txtAlbumHeaderText.setVisibility(View.GONE);
            if(showHeader){
                txtAlbumHeaderText.setVisibility(View.VISIBLE);
                txtAlbumHeaderText.setText(headerText + track.getDiskNumber().toString());
            }

            if(track.getTrackPosition() != null)
                txtTrackNumber.setText(track.getTrackPosition().toString()+".");

            txtTrackName.setText(track.getTitle());
            txtTrackArtist.setText(track.getArtist().getName());

            //should be converted into mm:ss
            txtTrackLength.setText(Converter.convertSeconds(track.getDuration()));

            //for passing the object to the click listener...
            view.setTag(track);
            view.setOnClickListener(itemClickListener);
        }
    }
}