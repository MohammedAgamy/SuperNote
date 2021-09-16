package com.example.supernote.pojo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.supernote.pojo.RoomDataBase.Dao;
import com.example.supernote.pojo.RoomDataBase.Entity;
import com.example.supernote.pojo.RoomDataBase.RoomDataBaseNote;

import java.util.List;

public class Repository {
    public Dao mDao;

    LiveData<List<Entity>> getAllData;

    public Repository(Context context) {
        RoomDataBaseNote db = RoomDataBaseNote.getInstance(context);
        mDao = db.dao();
        getAllData = mDao.selectAll();
    }


    //insert
    public void insert(Entity entity) {
        new InsertDataAsyncTask(mDao).execute(entity);
    }


    //class AsyncTask
    public static class InsertDataAsyncTask extends AsyncTask<Entity, Void, Void> {

        private Dao mDao;

        public InsertDataAsyncTask(Dao dao) {
            this.mDao = dao;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            mDao.insert(entities[0]);
            return null;
        }
    }

    //Update

    public void update(Entity entity) {
        new UpdateDataAsyncTask(mDao).execute(entity);
    }

    public static class UpdateDataAsyncTask extends AsyncTask<Entity, Void, Void> {
        Dao mDao;

        public UpdateDataAsyncTask(Dao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            mDao.update(entities[0]);
            return null;
        }
    }


    //delete

    public void delete(Entity entity)
    {
        new DeleteDataAsyncTask(mDao).execute(entity);
    }


    public static class  DeleteDataAsyncTask extends AsyncTask<Entity ,Void ,Void>
    {
        Dao dao ;

        public DeleteDataAsyncTask(Dao dao)
        {
            this.dao=dao;
        }

        @Override

        protected Void doInBackground(Entity... entities) {
            dao.delete(entities[0]);
            return null;
        }
    }

    //getAllData
    public LiveData<List<Entity>> getAllData()
    {
      return getAllData;
    }






}
