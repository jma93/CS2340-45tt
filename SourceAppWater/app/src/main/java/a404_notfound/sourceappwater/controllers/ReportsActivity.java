package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import a404_notfound.sourceappwater.R;

public class ReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        Button viewreport = (Button) findViewById(R.id.viewreport);
        viewreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), ViewReportActivity.class);
                startActivity(switchScreen);
            }
        });

        Button createreport = (Button) findViewById(R.id.createreport);
        createreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), CreateReportsActivity.class);
                startActivity(switchScreen);
            }
        });




    }
}
