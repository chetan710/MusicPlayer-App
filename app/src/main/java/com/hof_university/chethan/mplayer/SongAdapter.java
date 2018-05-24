package com.hof_university.chethan.mplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chethan on 05-03-2018.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    ArrayList<SongInfo> _songs;
    Context context;

    OnItemClickListener onItemClickListener;

    SongAdapter(Context context, ArrayList<SongInfo> _songs){
        this.context = context;
        this._songs = _songs;
    }

    public interface OnItemClickListener{
        void onItemClick(Button b, View v, SongInfo obj, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View myview = LayoutInflater.from(context).inflate(R.layout.row_song,viewGroup,false);
        return new SongHolder(myview);
    }

    @Override
    public void onBindViewHolder(final SongHolder songHolder, final int i) {
        final SongInfo c = _songs.get(i);
        songHolder.songName.setText(c.songName);
        songHolder.artistName.setText(c.artistName);
        songHolder.btnAction.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v){
               if (onItemClickListener!=null){
                   onItemClickListener.onItemClick(songHolder.btnAction,v,c,i);
               }
           }
        });
    }

    @Override
    public int getItemCount() {
        return _songs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView songName,artistName;
        Button btnAction;

        public SongHolder(View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.tvSongName);
            artistName = (TextView) itemView.findViewById(R.id.tvArtistName);
            btnAction = (Button) itemView.findViewById(R.id.btnAction);
        }
    }
}
