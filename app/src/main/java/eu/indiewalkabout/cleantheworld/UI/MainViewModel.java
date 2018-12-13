package eu.indiewalkabout.cleantheworld.UI;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import eu.indiewalkabout.cleantheworld.data.CleanWorldDb;
import eu.indiewalkabout.cleantheworld.data.CollectionEntry;


/**
 * -------------------------------------------------------------------------------------------------
 * ViewModel Class for retrieving all items in db
 * -------------------------------------------------------------------------------------------------
 */
public class MainViewModel extends AndroidViewModel {

    // tag for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<CollectionEntry>> collections;


    /**
     * ---------------------------------------------------------------------------------------------
     * Viewmodel class Constructor : init the attributes with dao
     * @param application
     * ---------------------------------------------------------------------------------------------
     */
    public MainViewModel(Application application) {
        super(application);
        CleanWorldDb cleanWorldDb = CleanWorldDb.getsDbInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the collections from db");
        collections = cleanWorldDb.cleanDbDao().loadAllCollections();
    }

    /**
     * ---------------------------------------------------------------------------------------------
     * Public method to retrieve the collections through ViewModel
     * @return
     * ---------------------------------------------------------------------------------------------
     */
    public LiveData<List<CollectionEntry>> getCollections() {
        return collections;
    }

}
