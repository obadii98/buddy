package com.example.buddytest2;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NearActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    Button homeBtn, calBtn, proBtn, nearBtn;
    FloatingActionButton Gps_button;
    private GestureDetectorCompat mgestureDetector;
    private ConstraintLayout near_layout;
    private float x;
    private GoogleMap map;
    LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near);

        //gps manage init
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        ///map init
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //button init
        Gps_button = findViewById(R.id.Locationbutton);
        Gps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (map == null) {
                    mapFragment.getMapAsync(NearActivity.this);
                }

                /// take permission for using location
                if (ContextCompat.checkSelfPermission(NearActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    if (ActivityCompat.shouldShowRequestPermissionRationale(NearActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {


                        ///if we need to explian why and users deny for first time
                        new AlertDialog.Builder(NearActivity.this).setTitle("Required Location Permission")
                                .setMessage("You have to give the Permission to know GYM locations ")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(NearActivity.this,
                                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();


                    } else {
/////////////////////////request permission for first time
//

                        ActivityCompat.requestPermissions(NearActivity.this,
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);


                    }
                } else {

                    //Gps on
                    if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //there is a internet
                            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                            Criteria criteria = new Criteria();

                            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
                            map.setMyLocationEnabled(true);
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));

                        } else {
                            Alert_Internet(NearActivity.this);

                        }


                    } else
                        //to let user turn on Gps
                        Alert_Gps(NearActivity.this);
                }


            }
        });


        homeBtn = findViewById(R.id.homebtn);
        calBtn = findViewById(R.id.calbtn);
        proBtn = findViewById(R.id.probtn);
        nearBtn = findViewById(R.id.nearbtn);

        homeBtn.setOnClickListener(this);
        calBtn.setOnClickListener(this);
        proBtn.setOnClickListener(this);
        nearBtn.setOnClickListener(this);

        mgestureDetector = new GestureDetectorCompat(this, new NearActivity.GestureListener());
        near_layout = (ConstraintLayout) findViewById(R.id.nearlayout);

        near_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                x = motionEvent.getX();
                if ((view.getWidth() / 2) > x) {
                    onSwipeLeft();
                }
                return true;
            }
        });



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng damas = new LatLng(33.510414, 36.278336);
        map.addMarker(new MarkerOptions().position(damas).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(damas));
        map.getUiSettings().setMyLocationButtonEnabled(true);


    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float difX = e2.getX() - e1.getX();
            if (difX > 0) {
                onSwipeLeft();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    private void onSwipeLeft() {
        Intent pro = new Intent(this, ProActivity.class);
        startActivity(pro);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    @Override
    public void finish() {
        super.finish();
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mgestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homebtn: {
                //changing intent
                Intent home = new Intent(this, HomeActivity.class);
                startActivity(home);

                //color of navbar
                homeBtn.setBackgroundColor(Color.parseColor("#D81B60"));
                nearBtn.setBackgroundColor(Color.LTGRAY);
                proBtn.setBackgroundColor(Color.LTGRAY);
                calBtn.setBackgroundColor(Color.LTGRAY);
                break;
            }
            case R.id.calbtn: {
                //changing intent
                Intent cal = new Intent(this, CalActivity.class);
                startActivity(cal);

                //color of navbar
                homeBtn.setBackgroundColor(Color.LTGRAY);
                calBtn.setBackgroundColor(Color.parseColor("#D81B60"));
                proBtn.setBackgroundColor(Color.LTGRAY);
                nearBtn.setBackgroundColor(Color.LTGRAY);
                break;
            }
            case R.id.probtn: {
                //changing intent
                Intent pro = new Intent(this, ProActivity.class);
                startActivity(pro);

                //color of navbar
                homeBtn.setBackgroundColor(Color.LTGRAY);
                nearBtn.setBackgroundColor(Color.LTGRAY);
                proBtn.setBackgroundColor(Color.parseColor("#D81B60"));
                calBtn.setBackgroundColor(Color.LTGRAY);
                break;
            }
            case R.id.nearbtn: {
                //changing intent
                Intent near = new Intent(this, NearActivity.class);
                startActivity(near);

                //color of navbar
                homeBtn.setBackgroundColor(Color.LTGRAY);
                calBtn.setBackgroundColor(Color.LTGRAY);
                proBtn.setBackgroundColor(Color.LTGRAY);
                nearBtn.setBackgroundColor(Color.parseColor("#D81B60"));
                break;
            }
        }
    }


    private void Alert_Gps(final Context context) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is Off");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");


        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();


    }

    private void Alert_Internet(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Internet if Off ");
        alertDialog.setMessage("There is no internet connection,You need internet connection to know your location..! ");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

}
