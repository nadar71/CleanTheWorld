package eu.indiewalkabout.cleantheworld.UI;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import eu.indiewalkabout.cleantheworld.R;
import eu.indiewalkabout.cleantheworld.data.CleanWorldDb;
import eu.indiewalkabout.cleantheworld.data.CollectionEntry;
import eu.indiewalkabout.cleantheworld.util.AppExecutors;
import eu.indiewalkabout.cleantheworld.util.ConsentSDK;

import java.util.Date;

public class InsertCollectionActivity extends AppCompatActivity {

    final static String TAG = InsertCollectionActivity.class.getSimpleName();

    // Views references
    ImageButton plasticInsertionBtn, otherInsertionBtn;
    TextView    plasticNumItems_Label, otherNumItems_Label, plasticNumItemsDesc_Label, otherNumItemsDesc_Label;
    TextView    plasticNumItems, otherNumItems, plasticNumItemsDesc, otherNumItemsDesc;
    Button      saveBtn;

    // Db instance reference
    private CleanWorldDb cleanWorldDb;

    // admob banner ref
    private AdView mAdView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        // Init UI item
        initViews();

        // load ads banner
        mAdView = findViewById(R.id.adView);

        // You have to pass the AdRequest from ConsentSDK.getAdRequest(this) because it handle the right way to load the ad
        mAdView.loadAd(ConsentSDK.getAdRequest(InsertCollectionActivity.this));

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
        plasticInsertionBtn = (ImageButton) findViewById(R.id.plastic_Btn);
        otherInsertionBtn   = (ImageButton) findViewById(R.id.other_Btn);

        plasticNumItems_Label     = (TextView)findViewById(R.id.howManyPlastic_Label);
        plasticNumItemsDesc_Label = (TextView)findViewById(R.id.plasticItem_Descrip_Label);
        plasticNumItems           = (TextView)findViewById(R.id.plasticItems_EditText);
        plasticNumItemsDesc       = (TextView)findViewById(R.id.plasticItem_Descrip_EditText);

        otherNumItems_Label       = (TextView)findViewById(R.id.howManyOther_Label);
        otherNumItemsDesc_Label   = (TextView)findViewById(R.id.otherItem_Descrip_Label);
        otherNumItems             = (TextView)findViewById(R.id.otherItems_EditText);
        otherNumItemsDesc         = (TextView)findViewById(R.id.otherItems_Descrip_EditText);

        saveBtn                   = (Button)  findViewById(R.id.save_Btn);


        // set click callbacks
        plasticInsertionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plasticNumItems_Label.setVisibility(View.VISIBLE);
                plasticNumItemsDesc_Label.setVisibility(View.VISIBLE);
                plasticNumItems.setVisibility(View.VISIBLE);
                plasticNumItemsDesc.setVisibility(View.VISIBLE);

                saveBtn.setVisibility(View.VISIBLE);

                otherInsertionBtn.setVisibility(View.INVISIBLE);
            }
        });

        otherInsertionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherNumItems_Label.setVisibility(View.VISIBLE);
                otherNumItemsDesc_Label.setVisibility(View.VISIBLE);
                otherNumItems.setVisibility(View.VISIBLE);
                otherNumItemsDesc.setVisibility(View.VISIBLE);

                saveBtn.setVisibility(View.VISIBLE);

                plasticInsertionBtn.setVisibility(View.INVISIBLE);
            }
        });


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

                if (otherNumItems.getText().length() > 0){
                    numItems = Integer.parseInt(otherNumItems.getText().toString());
                    description = otherNumItemsDesc.getText().toString();
                    isPlastic = false;
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

                // prepare for a new entry
                saveBtn.setVisibility(View.INVISIBLE);
                otherInsertionBtn.setVisibility(View.VISIBLE);
                plasticInsertionBtn.setVisibility(View.VISIBLE);

                plasticNumItems_Label.setVisibility(View.INVISIBLE);
                plasticNumItemsDesc_Label.setVisibility(View.INVISIBLE);
                plasticNumItems.setVisibility(View.INVISIBLE);
                plasticNumItemsDesc.setVisibility(View.INVISIBLE);

                otherNumItems_Label.setVisibility(View.INVISIBLE);
                otherNumItemsDesc_Label.setVisibility(View.INVISIBLE);
                otherNumItems.setVisibility(View.INVISIBLE);
                otherNumItemsDesc.setVisibility(View.INVISIBLE);

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
