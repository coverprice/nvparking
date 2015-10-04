package com.jamesrussell1911.noevalleyparking;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class NoeValleyParkingActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MapView(this));
    }
}

