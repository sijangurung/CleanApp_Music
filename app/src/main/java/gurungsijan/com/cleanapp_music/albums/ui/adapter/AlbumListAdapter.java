package gurungsijan.com.cleanapp_music.albums.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gurungsijan.com.cleanapp_music.R;
import gurungsijan.com.cleanapp_music.domain.models.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder> {

    List<Album> list;
    private View.OnClickListener itemClickListener;

    public AlbumListAdapter(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void updateList(List<Album> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.bind(list.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        private View view;
        ImageView imgAlbumCover;
        TextView txtAlbumName;
        TextView txtArtistNames;

        AlbumViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            txtAlbumName = (TextView) itemView.findViewById(R.id.txtAlbumName);
            txtArtistNames = (TextView) itemView.findViewById(R.id.txtAlbumArtist);
            imgAlbumCover = (ImageView) itemView.findViewById(R.id.imgAlbumCover);
        }

        void bind(Album album, View.OnClickListener itemClickListener) {
            txtAlbumName.setText(album.getTitle());
            txtArtistNames.setText(album.getReleaseDate());
            //for passing the object to the click listener...
            view.setTag(album);
            view.setOnClickListener(itemClickListener);

            if (album.getCover() != null)
                Picasso.with(view.getContext()).load(album.getCover()).into(imgAlbumCover);
            else if (album.getCoverMedium() != null) //Just for backup!!!
                Picasso.with(view.getContext()).load(album.getCoverMedium()).into(imgAlbumCover);
        }
    }
}