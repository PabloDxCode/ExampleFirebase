package com.example.pgutierrezd.exampleloginfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = null;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button btnLogOut;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        btnLogOut = (Button) findViewById(R.id.btnLogout);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG Inicio", "onAuthStateChanged:signed_in:" + user.getUid());
                    //writeNewUser(mAuth.getCurrentUser().getEmail(),"nada");
                } else {
                    // User is signed out
                    Log.d("TAG Inicio", "onAuthStateChanged:signed_out");
                    //startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                }
                // ...
            }
        };

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
            }
        });
    }

    private void writeNewUser(String email, String password) {
        User user = new User(email, password);
        DatabaseReference sd = myRef.push();
        database.getReference("User").child(sd.getKey()).setValue(user);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
