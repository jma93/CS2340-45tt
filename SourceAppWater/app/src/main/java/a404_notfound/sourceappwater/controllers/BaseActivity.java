package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import a404_notfound.sourceappwater.R;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BaseActivity extends AppCompatActivity {
    private DatabaseReference mRef;
    private  FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final String TAG = "Info";

    // Firebase Objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private TextView usename;
    private TextView address;
    private TextView coordinates;
    private TextView accountType;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);



        // Set up fire base Authentication and Listener for that authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        Button mEditInfo = (Button) findViewById(R.id.editinfobutton);
        mEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), EditInfoActivity.class);
                startActivity(switchScreen);
            }
        });

        mRef = database.getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);

                String n = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("name").getValue().toString();
                String m = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("addrs").getValue().toString();
                String o = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("coor").getValue().toString();
                String p = dataSnapshot.child("users").child(mAuth.getCurrentUser().getUid()).child("accttype").getValue().toString();
                usename.setText(n);
                address.setText(m);
                coordinates.setText(mAuth.getCurrentUser().getEmail());
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
        mAuth.addAuthStateListener(mAuthListener);
        usename = (TextView) findViewById(R.id.username1);
        address = (TextView) findViewById(R.id.address);
        coordinates = (TextView) findViewById(R.id.coordinates);
        accountType = (TextView) findViewById(R.id.accoungtype);
    }

    //Stop the firbase Listener
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
