package eu.indiewalkabout.cleantheworld.data;

<<<<<<< HEAD
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {CollectionEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class CleanWorldDb extends RoomDatabase {
    private static final String TAG = CleanWorldDb.class.getSimpleName();
    // lock for synchro
    private static final Object LOCK   = new Object();
    // private static final String DBNAME = "CleanWorldDB";
    private static final String DBNAME = "CleanWDB";
    private static CleanWorldDb sDbInstance ;

    public static CleanWorldDb getsDbInstance(Context context){
        if(sDbInstance == null){
            synchronized (LOCK){
                Log.d(TAG, "Creating App db singelton instance...");
                sDbInstance = Room.databaseBuilder(context.getApplicationContext(), CleanWorldDb.class,CleanWorldDb.DBNAME)
                        .allowMainThreadQueries() // TODO : temporary for debugging, delete this
                        .build();
            }

        }
        Log.d(TAG, "Db created");
        return sDbInstance;
    }

    public abstract CleanDbDao cleanDbDao();

=======
import android.arch.persistence.room.RoomDatabase;

public abstract class CleanWorldDb extends RoomDatabase {
>>>>>>> bf9558a5a8336c716856ee8a13f20b70ccde2398
}
