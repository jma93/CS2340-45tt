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
import a404_notfound.sourceappwater.model.FirbaseUtility;

public class EditInfoActivity extends DrawerActivity {

    private EditText mName;
    private EditText mAddrs;
    private EditText mCoord;

    private static final String TAG = "Info";

    private FirbaseUtility fbinstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mName = (EditText) findViewById(R.id.editname);
        mAddrs = (EditText) findViewById(R.id.editaddrs);
        mCoord = (EditText) findViewById(R.id.editcoor);
        fbinstance = new FirbaseUtility();

        Button changes = (Button) findViewById(R.id.submitchangesbttn);
        changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo();
                Intent switchScreen = new Intent(getApplicationContext(), BaseActivity.class);
                startActivity(switchScreen);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        fbinstance.addAuthListner();
    }

    //Stop the firbase Listener
    @Override
    public void onStop() {
        super.onStop();
        fbinstance.removeAuthListener();
    }

    private void updateInfo() {
        String name = mName.getText().toString();
        String address = mAddrs.getText().toString();
        String coordinates  = mCoord.getText().toString();

        DatabaseReference useInfo = fbinstance.getmRef().child("/users").child(fbinstance.getUser());

        useInfo.child("/name").setValue(name);
        useInfo.child("addrs").setValue(address);
        useInfo.child("/coor").setValue(coordinates);
    }
    @Override
    protected int getcView() {
        return R.layout.activity_edit_info;
    }
}
