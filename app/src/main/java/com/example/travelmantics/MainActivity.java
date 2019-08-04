package com.example.travelmantics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    //variables that will contain the FirebaseDatabase object:
    private FirebaseDatabase mFirebaseDatabase;
    //variable  that will hold the database reference:
    private DatabaseReference mDatabaseReference;

    EditText txtTitle;
    EditText txtDescription;
    EditText txtPrice;
    TravelDeal deal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUtil.openFbReference("traveldeals");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;;

        txtTitle = (EditText)findViewById(R.id.txtTitle);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        txtPrice = (EditText) findViewById(R.id.txtPrice);

        Intent intent = getIntent();
        TravelDeal deal = (TravelDeal) intent.getSerializableExtra("Deal");
        if(deal == null){
            deal = new TravelDeal();
        }
        this.deal = deal;
        txtTitle.setText(deal.getTitle());
        txtDescription.setText(deal.getDescription());
        txtPrice.setText(deal.getPrice());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "DEAL SAVED", Toast.LENGTH_LONG).show();
                clean();//resets contents of the text fields after data has been sent to db
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void saveDeal() {
        //read contents of the EditTexts:
        String title = txtTitle.getText().toString();
        String description  = txtDescription.getText().toString();
        String price = txtPrice.getText().toString();
        //create a deal:
        TravelDeal deal = new TravelDeal(title,description,price,"");
        //call push() to insert an object to the db, then call setValue() passing the deal object:
        mDatabaseReference.push().setValue(deal);

    }

    private void clean() {
        //set all the EditText fields to empty strings
        txtTitle.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        //give focus to the txtTitle EditText
        txtTitle.requestFocus();

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
