package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import a404_notfound.sourceappwater.R;

public class EditInfoActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mAddrs;
    private EditText mCoord;

    private DatabaseReference mRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final String TAG = "Info";

    // Firebase Objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);


        mName = (EditText) findViewById(R.id.editname);
        mAddrs = (EditText) findViewById(R.id.editaddrs);
        mCoord = (EditText) findViewById(R.id.editcoor);

        Button changes = (Button) findViewById(R.id.submitchangesbttn);
        changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo();
                Intent switchScreen = new Intent(getApplicationContext(), BaseActivity.class);
                startActivity(switchScreen);
            }
        });

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

        mRef = database.getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
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
    }

    //Stop the firbase Listener
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void updateInfo() {
        String name = mName.getText().toString();
        String address = mAddrs.getText().toString();
        String coordinates  = mCoord.getText().toString();

        DatabaseReference useInfo = mRef.child("/users").child(mAuth.getCurrentUser().getUid());

        useInfo.child("/name").setValue(name);
        useInfo.child("addrs").setValue(address);
        useInfo.child("/coor").setValue(coordinates);
    }
}
