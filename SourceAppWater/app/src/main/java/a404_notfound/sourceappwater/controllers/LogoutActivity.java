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
import java.util.Map;


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

    private String userClassification;
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

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
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
                //DatabaseReference mRefChild = mRef.child(mAuth.getCurrentUser().getUid());
                createProfile((String) spinner.getSelectedItem());
                //mRefChild.setValue(tpe);
                Intent switchScreen = new Intent(getApplicationContext(), HqActivity.class);
                startActivity(switchScreen);
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


    private void createProfile(String ucas) {
        String name = mName.getText().toString();
        String a = "/users/";
        Map<String, Object> values = new HashMap<>();

        if (ucas == "Administrator") {
            Admin e = new Admin(name);
            String nme = e.getUsername();
            String addrs = e.getAddress();
            String coor = e.getCoordinates();
            String uid = mAuth.getCurrentUser().getUid();
            values.put(a + uid + "/name/", nme);
            values.put(a+ uid + "/addrs/", addrs);
            values.put(a + uid + "/coor/", coor);
            values.put(a + uid + "/accttype/", e.toString());
            mRef.updateChildren(values);
        } else if(ucas =="Worker") {
            Worker e = new Worker(name);
            String nme = e.getUsername();
            String addrs = e.getAddress();
            String coor = e.getCoordinates();
            String uid = mAuth.getCurrentUser().getUid();
            values.put(a + uid + "/name/", nme);
            values.put(a+ uid + "/addrs/", addrs);
            values.put(a + uid + "/coor/", coor);
            values.put(a + uid + "/accttype/", e.toString());
            mRef.updateChildren(values);
        } else if (ucas == "Manager") {
            Manager e = new Manager(name);
            String nme = e.getUsername();
            String addrs = e.getAddress();
            String coor = e.getCoordinates();
            String uid = mAuth.getCurrentUser().getUid();
            values.put(a + uid + "/name/", nme);
            values.put(a+ uid + "/addrs/", addrs);
            values.put(a + uid + "/coor/", coor);
            values.put(a + uid + "/accttype/", e.toString());
            mRef.updateChildren(values);
        } else {
            RegisteredUser e = new RegisteredUser(name);
            String nme = e.getUsername();
            String addrs = e.getAddress();
            String coor = e.getCoordinates();
            String uid = mAuth.getCurrentUser().getUid();
            values.put(a + uid + "/name/", nme);
            values.put(a+ uid + "/addrs/", addrs);
            values.put(a + uid + "/coor/", coor);
            values.put(a + uid + "/accttype/", e.toString());
            mRef.updateChildren(values);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        userClassification = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        userClassification = "User";
    }
}
