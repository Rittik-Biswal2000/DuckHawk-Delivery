package com.duckhawk.duckhawk_delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Map extends FragmentActivity implements OnMapReadyCallback,TaskLoadedCallback {

    GoogleMap map;
    String[] arrOfStr;
    Location curlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE=101;
    Polyline curpolyline=null;

    protected double curLat, curLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent i = getIntent();
        String location = i.getStringExtra("Location");

        arrOfStr = location.split(",", 2);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
        System.out.println(arrOfStr[0]);
        System.out.println(arrOfStr[1]);

    }

    private void fetchLastLocation() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null)
                {
                    curlocation = location;
                    Toast.makeText(getApplicationContext(),"Location: " + curlocation.getLatitude() + ","+curlocation.getLatitude(),Toast.LENGTH_LONG).show();

                    System.out.println(curlocation.getLatitude());
                    System.out.println(curlocation.getLongitude());
                    curLat = curlocation.getLatitude();
                    curLong = curlocation.getLongitude();

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(Map.this);

                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    fetchLastLocation();
                }
                break;

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        double v1 = Double.parseDouble(arrOfStr[0]);
        double v2 = Double.parseDouble(arrOfStr[1]);

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(curLat);
        System.out.println(curLong);
        LatLng order = new LatLng(v1,v2);
        LatLng curlocation = new LatLng(curLat,curLong);
        String url = geturl(curlocation,order,"driving");
        new FetchURL(Map.this).execute(url,"driving");
        map.addMarker(new MarkerOptions().position(order).title("To Deliver"));
        map.moveCamera(CameraUpdateFactory.newLatLng(order));
        map.animateCamera(CameraUpdateFactory.zoomTo(12.0f));
        map.addMarker(new MarkerOptions().position(curlocation).title("My Location"));
    }

    private String geturl(LatLng origin, LatLng destination, String directionMode) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_destination = "destination=" + destination.latitude + "," + destination.longitude;
        String str_mode = "mode=" + directionMode;
        String parameters = str_origin+"&"+str_destination+"&"+str_mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=AIzaSyC52Z3z1WF_y0Q0dbYfexizoexgAnSTov0";
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if(curpolyline!=null)
        {
            curpolyline.remove();
        }
        curpolyline = map.addPolyline((PolylineOptions)values[0]);
    }
}
