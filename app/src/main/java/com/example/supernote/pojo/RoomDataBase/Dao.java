package com.example.supernote.pojo.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(Entity entity);

    @Update
    void update(Entity entity);

    @Delete
    void delete(Entity entity);

    @Query("delete from Note")
    void deleteAll();

    @Query("select * from Note")
    LiveData<List<Entity>> selectAll();
}
