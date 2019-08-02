package com.example.travelmantics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //create instance of menuInflater object
        MenuInflater inflater = getMenuInflater();
        //pass it the XML resource we want to inflate
        inflater.inflate(R.menu.save_menu,menu);

        return true;
    }
}
