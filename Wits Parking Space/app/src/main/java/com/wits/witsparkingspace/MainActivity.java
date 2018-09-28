package com.wits.witsparkingspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void west_campus(View view){
        Intent intent = new Intent(this, activity_west_campus.class);
        startActivity(intent);
    }

    public void east_campus(View view){
        Intent intent = new Intent(this, activity_east_campus.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, activity_login.class);
        startActivity(intent);
    }
}
