package a404_notfound.sourceappwater.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Joshua on 2/23/2017.
 */

public class FirbaseUtility {

    //Firebase Database Objects
    private DatabaseReference mRef;

    // Firebase Authentication Objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "Info";

    public FirbaseUtility() {
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

        //Set Up Database For Session
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference();
    }

    public void addAuthListner() {
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void removeAuthListener() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public DatabaseReference getmRef() {
        return mRef;
    }

    public void setmRef(DatabaseReference mRef) {
        this.mRef = mRef;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public FirebaseAuth.AuthStateListener getmAuthListener() {
        return mAuthListener;
    }

    public void setmAuthListener(FirebaseAuth.AuthStateListener mAuthListener) {
        this.mAuthListener = mAuthListener;
    }

    public String getUser() {
        if (mAuth != null) {
            return mAuth.getCurrentUser().getUid();
        }
        return null;
    }

    public String getUserEmail(){
        if (mAuth != null) {
            return mAuth.getCurrentUser().getEmail();
        }
        return null;
    }
}
