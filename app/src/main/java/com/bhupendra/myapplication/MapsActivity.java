package com.bhupendra.myapplication;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity
         {

    private GoogleMap mMap;
    private ImageView img;
    private GoogleApiClient mLocationClient;
   // private TextView mMessageView;
    private MapView m;
    private Point p1;
    private LatLng ltlng;
    //double a=0,b=0;
  //  private Projection projection;


    // These settings are the same as the settings for the map. They will in fact give you updates
    // at the maximal rates currently possible.
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

             @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        img = (ImageView) findViewById(R.id.markerid);
                 setUpMapIfNeeded();
             }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int X = (int)img.getX();
                int Y =   (int)img.getY();
                p1 = new Point(X,Y);
                ltlng= mMap.getProjection().fromScreenLocation(p1);
                double a = ltlng.latitude;
                double b = ltlng.longitude;
                Toast.makeText(MapsActivity.this, ltlng.latitude + "," +
                        ltlng.latitude , Toast.LENGTH_LONG).show();
            }
        });



    }



    @Override
    public void onPause() {
        super.onPause();
        if (mLocationClient != null) {
            mLocationClient.disconnect();
        }
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);

            }
        }
    }


         }