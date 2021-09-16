package com.example.supernote.pojo.RoomDataBase;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.net.URL;

@androidx.room.Entity(tableName = "Note")
public class Entity {
    @PrimaryKey(autoGenerate = true)
    int id ;
    String title ;
    String textNote ;
    byte[] imageView ;

   /* public Entity(String title, String textNote) {
        this.title = title;
        this.textNote = textNote;
    }
*/
    public Entity(String title, String textNote, byte[] imageView) {
        this.title = title;
        this.textNote = textNote;
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public String getTextNote() {
        return textNote;
    }

    public byte[] getImageView() {
        return imageView;
    }

    public void setId(int id) {
        this.id = id;
    }
}
