package com.demo.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;

public class UsernameActivity extends AppCompatActivity {
    EditText username_edit_text;
    ImageView create_username;
    TextView text;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        username_edit_text = (EditText) findViewById(R.id.username_edit_text);
        create_username = (ImageView) findViewById(R.id.create_username);
        text = (TextView) findViewById(R.id.text);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        create_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = username_edit_text.getText().toString();
                createUsername(string);
            }
        });
    }

    private void createUsername(final String string) {
        /*
        * Check if username exists or not
        * */
        databaseReference.child("usernames").child(string).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    final boolean[] isUsernameCreated = {false};

                    /*
                    * Check for first 5 usernames.
                    * For eg: if our string == "Spidey"
                    * we'll check first for Spidey1, the Spidey2 and so on upto Spidey5
                    * */
                    for (int i = 1; i <= 5; i++) {
                        final String username = string + i;

                        /*
                        * If username is created break from loop
                        * */
                        if (isUsernameCreated[0])
                            break;

                        final int finalI = i;
                        databaseReference.child("usernames").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                /*
                                * If username ( = string + i) doesn't exist set username and change isUsernameCreated to true
                                * */
                                if (!dataSnapshot.exists() && !isUsernameCreated[0]) {
                                    databaseReference.child("usernames").child(username).setValue(true);
                                    isUsernameCreated[0] = true;
                                    text.setText("You new username is = " + username + "");
                                }

                                /*
                                * If all the 5 usernames(e.g upto Spidey5) exists then create a new username using timestamp;
                                * which will always be unique
                                * */
                                if (finalI == 5 && !isUsernameCreated[0]) {
                                    String timestamp = String.valueOf(System.currentTimeMillis());
                                    databaseReference.child("usernames").child(string + "_" + timestamp).setValue(true);
                                    text.setText("You new username is = " + string + "_" + timestamp + "");
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }

                } else {
                    databaseReference.child("usernames").child(string).setValue(true);
                    text.setText("You new username is = " + string + "");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
