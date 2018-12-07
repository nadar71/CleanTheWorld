package eu.indiewalkabout.cleantheworld.data;

<<<<<<< HEAD
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
    List<CollectionEntry> loadAllCollections();

    @Insert
    void insertCollection(CollectionEntry collectionEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCollection(CollectionEntry collectionEntry);

    @Delete
    void deleteCollection(CollectionEntry collectionEntry);

=======
public interface CleanDbDao {
>>>>>>> bf9558a5a8336c716856ee8a13f20b70ccde2398
}
