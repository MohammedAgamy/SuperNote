package com.example.supernote.pojo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.supernote.R;
import com.example.supernote.pojo.RoomDataBase.Entity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdpterNote extends RecyclerView.Adapter<AdpterNote.item_note> {
    List<Entity> mList;
    Context mContext;

    public AdpterNote(List<Entity> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @NotNull
    @Override
    public item_note onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        item_note item_view = new item_note(rowItem);
        return item_view;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull item_note holder, int position) {
        holder.mTitel.setText(mList.get(position).getTitle());
        holder.mNote.setText(mList.get(position).getTextNote());
        //holder.imageNote.setImageBitmap(ConvertImage.bitmapByteArray(mList.get(position).getImageView()));
        Glide.with(mContext).load(ConvertImage.bitmapByteArray(mList.get(position).getImageView())).into(holder.imageNote);
        holder.mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext, "hi", Toast.LENGTH_SHORT).show();
                return true ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class item_note extends RecyclerView.ViewHolder {
        TextView mTitel, mNote;
        ImageView mImageView ,imageNote;

        public item_note(@NonNull @NotNull View itemView) {
            super(itemView);
            mTitel = itemView.findViewById(R.id.title);
            mNote = itemView.findViewById(R.id.tetNote);
            mImageView = itemView.findViewById(R.id.imageDelete);
            imageNote = itemView.findViewById(R.id.imageNote_item);



        }
    }
}
