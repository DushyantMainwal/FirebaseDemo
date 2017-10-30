package com.demo.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button search_bar, unique_username, firebase_storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_bar = (Button) findViewById(R.id.search_bar);
        unique_username = (Button) findViewById(R.id.unique_username);
        firebase_storage = (Button) findViewById(R.id.firebase_storage);

        search_bar.setOnClickListener(this);
        unique_username.setOnClickListener(this);
        firebase_storage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_bar:
                startActivity(new Intent(this, SearchBarActivity.class));
                break;
            case R.id.unique_username:
                startActivity(new Intent(this, UsernameActivity.class));
                break;
            case R.id.firebase_storage:
                startActivity(new Intent(this, StorageActivity.class));
                break;
        }
    }
}
