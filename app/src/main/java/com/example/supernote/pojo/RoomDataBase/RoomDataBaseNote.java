package com.example.supernote.pojo.RoomDataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

@Database(entities = Entity.class, version = 4)
public abstract class RoomDataBaseNote extends RoomDatabase {
    private static RoomDataBaseNote instance;

    public abstract Dao dao();

    public static synchronized RoomDataBaseNote getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RoomDataBaseNote.class,"Note")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }

        return instance;
    }


public static RoomDatabase.Callback callback=new Callback() {
    @Override
    public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        new PopulateDataAsyncTask(instance).execute();
    }
};

    public static class PopulateDataAsyncTask extends AsyncTask<Void ,Void,Void>
    {

        private  Dao mdao ;
        PopulateDataAsyncTask(RoomDataBaseNote roomDataBase)
        {

            mdao=roomDataBase.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}
