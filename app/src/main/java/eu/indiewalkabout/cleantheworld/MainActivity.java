package eu.indiewalkabout.cleantheworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton collectBtn = null;
    ImageButton infoBtn    = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectBtn = findViewById(R.id.collectBtn);
        infoBtn    = findViewById(R.id.infoBtn);

        // Open collection form
        collectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openInsert = new Intent(MainActivity.this, InsertActivity.class);
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




}
