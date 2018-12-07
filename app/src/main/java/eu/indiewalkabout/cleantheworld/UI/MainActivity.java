package eu.indiewalkabout.cleantheworld.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import eu.indiewalkabout.cleantheworld.R;
import eu.indiewalkabout.cleantheworld.data.CleanWorldDb;
import eu.indiewalkabout.cleantheworld.data.CollectionEntry;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // views references
    private ImageButton collectBtn = null;
    private ImageButton infoBtn    = null;
    private TextView    plasticCollectedNum, otherCollectedNum;

    // total number of items collected till now
    int plasticItemsNumStored, otherGbItemsNumStored;

    // todo : change with livedata : db reference
    CleanWorldDb cleanWorldDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init views
        collectBtn          = (ImageButton) findViewById(R.id.collectBtn);
        infoBtn             = (ImageButton) findViewById(R.id.infoBtn);
        plasticCollectedNum = (TextView)findViewById(R.id.plasticNum_Label);
        otherCollectedNum   = (TextView)findViewById(R.id.otherGbNum_Label);

        // Open collection form
        collectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openInsert = new Intent(MainActivity.this, InsertCollectionActivity.class);
                startActivity(openInsert);
            }
        });

        // Open info form
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openInfo = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(openInfo);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        // todo : change with livedata :
        plasticItemsNumStored = 0;
        otherGbItemsNumStored = 0;
        cleanWorldDb = CleanWorldDb.getsDbInstance(getApplicationContext());

        // todo : change with livedata : Show log list of db entry for debug
        List<CollectionEntry> collections = cleanWorldDb.cleanDbDao().loadAllCollections();
        if (collections.size() > 0 ) {
            getDbEntryList(collections);
        } else {
            Log.d(TAG, "Db empty");
        }

        // show items collected currently
        plasticCollectedNum.setText(Integer.toString(plasticItemsNumStored));
        otherCollectedNum.setText(Integer.toString(otherGbItemsNumStored));

    }



    // todo : change with livedata :
    private void getDbEntryList(List<CollectionEntry> collection){

        for (CollectionEntry entry : collection) {
            // debug : todo : delete
            Log.d(TAG, "Entry id : " + entry.getId() +
                    " num of pieces : " + entry.getNum_of_pieces() +
                    " inserted at : "   + entry.getInsertedAt() +
                    " is plastic ? : "  + entry.isPlastic()
            );

            if (entry.isPlastic() == true)
                plasticItemsNumStored += entry.getNum_of_pieces();
            else
                otherGbItemsNumStored += entry.getNum_of_pieces();

        }


    }



    // ---------------------------------------------------------------------------------------------
    //                                          MENU STUFF
    // ---------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent settingsIntent = new Intent(this, MainSettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
