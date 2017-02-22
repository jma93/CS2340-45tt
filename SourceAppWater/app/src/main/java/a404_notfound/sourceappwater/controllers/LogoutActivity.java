package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.Worker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import a404_notfound.sourceappwater.model.*;

public class LogoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatabaseReference mRef;
    private  FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final String TAG = "Info";
    public static List<String> userCert = Arrays.asList("User", "Worker", "Manager", "Administrator");

    // Firebase Objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String _userClassification;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        mName = (EditText) findViewById(R.id.nameofuser);
        mName.setOnEditorActionListener(new TextView.OnEditorActionListener()  {
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userCert);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
        Button mUpdateButton = (Button) findViewById(R.id.logout_button);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mRefChild = mRef.child(mAuth.getCurrentUser().getUid());
                String tpe = createProfile();
                mRefChild.setValue(tpe);
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


    private String createProfile() {
        Object e;
        String name = mName.getText().toString();
        String ucas = _userClassification;
        if (ucas == "Admin") {
            e = new Admin(name);
        } else if(ucas =="Worker") {
            e = new Worker(name);
        } else if (ucas == "Manager") {
            e = new Manager(name);
        } else {
            e = new RegisteredUser(name);
        };
        return e.toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        _userClassification = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        _userClassification = "User";
    }
}
