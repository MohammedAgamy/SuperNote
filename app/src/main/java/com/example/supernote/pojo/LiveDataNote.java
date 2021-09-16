package com.example.supernote.pojo;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.supernote.pojo.RoomDataBase.Entity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LiveDataNote extends AndroidViewModel {

    Repository mRepository ;
    LiveData<List<Entity>> mLiveData ;

    public LiveDataNote(@NonNull @NotNull Application application) {
        super(application);
        mRepository=new Repository(application);
        mLiveData=mRepository.getAllData;
    }

    public void insert(Entity entity)
    {
        mRepository.insert(entity);
    }

    public LiveData<List<Entity>> getAllData()
    {
        return mRepository.getAllData ;
    }
}



