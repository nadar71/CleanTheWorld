package eu.indiewalkabout.cleantheworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    ImageButton plasticInsertionBtn, otherInsertionBtn;
    TextView plasticNumItems, otherNumItems;
    Button   saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        // recover UI items references
        plasticInsertionBtn = (ImageButton) findViewById(R.id.plasticBtn);
        otherInsertionBtn   = (ImageButton) findViewById(R.id.otherBtn);
        plasticNumItems     = (TextView)    findViewById(R.id.plasticItemsText);
        otherNumItems       = (TextView)    findViewById(R.id.otherItemsText);
        saveBtn             = (Button)      findViewById(R.id.saveBtn);


        plasticInsertionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plasticNumItems.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
            }
        });

        otherInsertionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherNumItems.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numPlastics=0, numOthers=0;
                if (plasticNumItems.getText().length()>0){
                    numPlastics = Integer.parseInt(plasticNumItems.getText().toString());
                }
                if (otherNumItems.getText().length()>0){
                    numOthers = Integer.parseInt(otherNumItems.getText().toString());
                }

                Toast.makeText(InsertActivity.this, "Plastic items : "+numPlastics+" Others items : "+numOthers, Toast.LENGTH_SHORT).show();
                otherNumItems.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
            }
        });


    }
}
