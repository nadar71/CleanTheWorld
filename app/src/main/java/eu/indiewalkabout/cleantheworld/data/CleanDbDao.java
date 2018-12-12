package eu.indiewalkabout.cleantheworld.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;

@Dao
public interface CleanDbDao {

    @Query("SELECT * FROM COLLECTIONS ORDER BY INSERTED_AT")
    LiveData<List<CollectionEntry>> loadAllCollections();

    @Insert
    void insertCollection(CollectionEntry collectionEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCollection(CollectionEntry collectionEntry);

    @Delete
    void deleteCollection(CollectionEntry collectionEntry);

}
