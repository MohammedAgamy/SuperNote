package com.example.supernote.pojo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supernote.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class AdapterPlayList extends RecyclerView.Adapter<AdapterPlayList.item_playList> {

    File[] mListFile;
    TimeAgo timeAgo;
    OnItemClick onItemClick ;

    public AdapterPlayList(File[] mListFile ,OnItemClick onItemClick) {
        this.mListFile = mListFile;
        this.onItemClick=onItemClick ;
    }

    @NonNull
    @NotNull
    @Override
    public item_playList onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        item_playList item_view = new item_playList(rowItem);
        timeAgo =new TimeAgo();
        return item_view;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull item_playList holder, int position) {

        holder.mFileName.setText(mListFile[position].getName());
        holder.mData.setText(timeAgo.getTimeAgo(mListFile[position].lastModified()));

    }

    @Override
    public int getItemCount() {
        return mListFile.length;
    }

    class item_playList extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mFileName, mData;

        public item_playList(@NonNull @NotNull View itemView) {
            super(itemView);

            mFileName = itemView.findViewById(R.id.fileName);
            mData = itemView.findViewById(R.id.data);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
               onItemClick.onClickLisiner(mListFile[getAdapterPosition()] , getAdapterPosition());
        }
    }


    public interface OnItemClick
    {
        void onClickLisiner( File allFile,int position) ;
    }
}
