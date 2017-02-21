package gurungsijan.com.cleanapp_music.artists.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gurungsijan.com.cleanapp_music.R;
import gurungsijan.com.cleanapp_music.domain.models.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sijan Gurung on 10/02/2017.
 * Shortcut AS
 * sijan.gurung@shortcut.no
 */
public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ArtistViewHolder> {

    List<Artist> list;
    private View.OnClickListener itemClickListener;

    public ArtistListAdapter(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void updateList(List<Artist> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        holder.bind(list.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {

        private View view;
        ImageView artistImage;
        TextView artistName;

        ArtistViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            artistName = (TextView) itemView.findViewById(R.id.txtArtistName);
            artistImage = (ImageView) itemView.findViewById(R.id.imgArtistPic);
        }

        void bind(Artist artist, View.OnClickListener itemClickListener) {
            artistName.setText(artist.getName());
            //for passing the object to the click listener...
            view.setTag(artist);
            view.setOnClickListener(itemClickListener);

            if(artist.getPicture() != null)
                Picasso.with(view.getContext()).load(artist.getPicture()).into(artistImage);
            else  if(artist.getPictureMedium() != null) //Just for backup!!!
                Picasso.with(view.getContext()).load(artist.getPictureMedium()).into(artistImage);

        }
    }
}