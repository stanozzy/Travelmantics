package com.example.travelmantics;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    //static members can be called w/out instantiating an abject of this class:
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static ArrayList<TravelDeal> mDeals;

    //private empty constructor to prevent this class from being instantiated
    //outside this class:
    private FirebaseUtil(){};

    public static void openFbReference(String ref){
        if (firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDeals = new ArrayList<TravelDeal>();
        }
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }

}
