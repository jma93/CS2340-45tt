package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirbaseUtility;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BaseActivity extends DrawerActivity {
      private static final String TAG = "Info";


    private TextView usename;
    private TextView address;
    private TextView coordinates;
    private TextView accountType;
    private FirbaseUtility fbinstance;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button mEditInfo = (Button) findViewById(R.id.editinfobutton);
        mEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), EditInfoActivity.class);
                startActivity(switchScreen);
            }
        });


        fbinstance = new FirbaseUtility();
        fbinstance.getmRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);

                String n = dataSnapshot.child("users").child(fbinstance.getUser()).child("name").getValue().toString();
                String m = dataSnapshot.child("users").child(fbinstance.getUser()).child("addrs").getValue().toString();
                String o = dataSnapshot.child("users").child(fbinstance.getUser()).child("coor").getValue().toString();
                String p = dataSnapshot.child("users").child(fbinstance.getUser()).child("accttype").getValue().toString();
                usename.setText(n);
                address.setText(m);
                coordinates.setText(fbinstance.getUserEmail());
                accountType.setText(p);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        fbinstance.addAuthListner();
        usename = (TextView) findViewById(R.id.username1);
        address = (TextView) findViewById(R.id.address);
        coordinates = (TextView) findViewById(R.id.coordinates);
        accountType = (TextView) findViewById(R.id.accoungtype);
    }

    //Stop the firbase Listener
    @Override
    public void onStop() {
        super.onStop();
        fbinstance.removeAuthListener();
    }

    @Override
    protected int getcView() {
        return R.layout.activity_base;
    }
}
