package eu.indiewalkabout.cleantheworld.UI;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

import eu.indiewalkabout.cleantheworld.R;
import eu.indiewalkabout.cleantheworld.data.CleanWorldDb;
import eu.indiewalkabout.cleantheworld.data.CollectionEntry;
import eu.indiewalkabout.cleantheworld.util.AppExecutors;
import eu.indiewalkabout.cleantheworld.util.ConsentSDK;

import java.util.Date;

public class InsertPlasticCollectionActivity extends AppCompatActivity {

    final static String TAG = InsertPlasticCollectionActivity.class.getSimpleName();

    // Views references
    TextView    plasticNumItems_Label, plasticNumItemsDesc_Label;
    TextView    plasticNumItems, plasticNumItemsDesc;
    Button      saveBtn, cancelBtn;

    // Db instance reference
    private CleanWorldDb cleanWorldDb;

    // admob banner ref
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_plastic);

        // Init UI item
        initViews();

        // load ads banner
        mAdView = findViewById(R.id.adView);

        // You have to pass the AdRequest from ConsentSDK.getAdRequest(this) because it handle the right way to load the ad
        mAdView.loadAd(ConsentSDK.getAdRequest(InsertPlasticCollectionActivity.this));

        // Db init
        cleanWorldDb = CleanWorldDb.getsDbInstance(getApplicationContext());

        ActionBar actionBar = this.getSupportActionBar();

        // Set the action bar back button to look like an up button
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


    /**
     * Init activity views and their event callbacks
     */
    private void initViews(){
        // recover UI items references

        plasticNumItems_Label     = findViewById(R.id.howManyPlastic_tv);
        plasticNumItemsDesc_Label = findViewById(R.id.plasticItem_Descrip_tv);
        plasticNumItems           = findViewById(R.id.plasticItems_et);
        plasticNumItemsDesc       = findViewById(R.id.plasticItem_Descrip_et);


        saveBtn                   = findViewById(R.id.save_btn);
        cancelBtn                 = findViewById(R.id.cancel_plastic_btn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // field var
                // final (needed for executor thread):
                final Date date = new Date();
                final int fNumItems ;
                final String fDescription ;
                final boolean fIsPlastic  ;

                // mutable :
                int numItems       = 0;
                String description = "";
                boolean isPlastic  = false;

                if (plasticNumItems.getText().length() > 0){
                    numItems = Integer.parseInt(plasticNumItems.getText().toString());
                    description = plasticNumItemsDesc.getText().toString();
                    isPlastic = true;
                }

                fNumItems    = numItems;
                fDescription = description;
                fIsPlastic   = isPlastic;

                // ----------------------------------------
                // Update db using executor
                // ----------------------------------------
                AppExecutors.getInstance().diskIO().execute(new Runnable() {

                    @Override
                    public void run() {
                        // create new object to insert
                        CollectionEntry collectionEntry = new CollectionEntry(fNumItems,fIsPlastic,date,fDescription );

                        // Call dao for insertion
                        cleanWorldDb.cleanDbDao().insertCollection(collectionEntry);
                        Log.d(TAG, "run: Items  collected: " + fNumItems + " Inserted in db.");

                    }

                });

                Toast.makeText(InsertPlasticCollectionActivity.this,
                        fNumItems + " of plastic garbage items SAVED!", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });

        // go back main in case of cancel, without saving nothing
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }



    // ---------------------------------------------------------------------------------------------
    //                                          MENU STUFF
    // ---------------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // When the home button is pressed, take the user back to Home
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

}
