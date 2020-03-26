package com.duckhawk.duckhawk_delivery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class Map extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    GoogleMap map;
    private GoogleApiClient mGoogleApiClient;
    private LocationManager mLocationManager;
    private LocationRequest mLocationRequest;
    private com.google.android.gms.location.LocationListener Listener;
    private long Update_Interval = 2000;
    private long Fastest_Interval = 5000;
    private LocationManager locationManager;
    private boolean isPermision;
    LatLng cLatLng;
    Button call, cancl,notofication;
    int c= 1;
    private static final int MY_PERMISSION_REQUEST_SEND_SMS=0;

    String[] arrOfStr;
    Location curlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Polyline curpolyline = null;
    Marker marker = null;
    TextView Prodid, Prodcat, Buyer, Address,Price,Distance;

    protected double curLat, curLong;
    String prodid, prodcat, address, buyer,phone,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent i = getIntent();
        String location = i.getStringExtra("Location");
        prodid = i.getStringExtra("Prodid");
        prodcat = i.getStringExtra("Prodcat");
        buyer = i.getStringExtra("Buyer");
        address = i.getStringExtra("Address");
        phone = i.getStringExtra("Phone");
        price = i.getStringExtra("Price");
        c = i.getIntExtra("zoom", 1);

        Prodcat = (TextView) findViewById(R.id.prodcat);
        Prodid = (TextView) findViewById(R.id.prodid);
        Address = (TextView) findViewById(R.id.address);
        Buyer = (TextView) findViewById(R.id.buyer);
        Price = (TextView)findViewById(R.id.price);
        call = (Button) findViewById(R.id.call);
        cancl = (Button) findViewById(R.id.delivery);
        notofication = (Button) findViewById(R.id.notification);
        Distance = (TextView)findViewById(R.id.distance);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    ActivityCompat.requestPermissions(Map.this,new String[]
                            {Manifest.permission.CALL_PHONE},REQUEST_CODE);
                }
                getApplicationContext().startActivity(intent);
            }
        });
        notofication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permision = ContextCompat.checkSelfPermission(Map.this,Manifest.permission.SEND_SMS);

                if(permision == PackageManager.PERMISSION_GRANTED)
                {
                    sendsms();
                }
                else
                {
                    ActivityCompat.requestPermissions(Map.this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSION_REQUEST_SEND_SMS);
                }
            }
        });

        Prodid.setText("Product Id: "+prodid);
        Prodcat.setText("Product Category: "+prodcat);
        Address.setText("Address: "+address);
        Buyer.setText("Buyer: "+buyer);
        Price.setText("Price: "+price);
        arrOfStr = location.split(",", 2);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
        System.out.println(arrOfStr[0]);
        System.out.println(arrOfStr[1]);

    }

    private void fetchLastLocation() {
        mGoogleApiClient  = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
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
                    Toast.makeText(getApplicationContext(),"Location: " + curlocation.getLatitude() + ","+curlocation.getLongitude(),Toast.LENGTH_LONG).show();

                    System.out.println(curlocation.getLatitude());
                    System.out.println(curlocation.getLongitude());
                    curLat = curlocation.getLatitude();
                    curLong = curlocation.getLongitude();
//
//                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//                    mapFragment.getMapAsync(Map.this);

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
            case MY_PERMISSION_REQUEST_SEND_SMS:
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Map.this,"Thanks for permitting",Toast.LENGTH_SHORT).show();
                    sendsms();
                }
                else
                {
                    Toast.makeText(Map.this,"Why didnt u permitted",Toast.LENGTH_SHORT).show();
                }
            }
            break;

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        double v1 = Double.parseDouble(arrOfStr[0]);
        double v2 = Double.parseDouble(arrOfStr[1]);

        if(c==1)
        {

            CameraPosition cameraPosition = new CameraPosition.Builder().target(cLatLng).zoom(10.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            map.moveCamera(cameraUpdate);
            c++;

        }
//        map.animateCamera(CameraUpdateFactory.zoomTo(25.0f));
        map.moveCamera(CameraUpdateFactory.newLatLng(cLatLng));
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(curLat);
        System.out.println(curLong);
        LatLng order = new LatLng(v1,v2);
        LatLng curlocation = new LatLng(curLat,curLong);
        String url = geturl(curlocation,order,"driving");
        new FetchURL(Map.this).execute(url,"driving");
        if(marker!=null)
        {
            marker.remove();
        }
        map.addMarker(new MarkerOptions().position(order).title("To Deliver"));
        marker = map.addMarker(new MarkerOptions().position(cLatLng).title("My Location"));

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
    public void onTaskDone(PolylineOptions values,String distance) {
        if(curpolyline!=null)
        {
            curpolyline.remove();
        }
        curpolyline = map.addPolyline((PolylineOptions)values);
        Toast.makeText(this,"Distance"+distance,Toast.LENGTH_SHORT).show();
        Distance.setText("  "+distance + " Away  ");
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdate();
        curlocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(curlocation==null)
        {
            startLocationUpdate();
        }
        else
        {
            Toast.makeText(this,"Location not detected: "+curlocation,Toast.LENGTH_SHORT).show();
        }
    }

    private void startLocationUpdate() {
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(Update_Interval)
                .setFastestInterval(Fastest_Interval);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        curlocation = location;

        String mssg = "Updated Location" + Double.toString(curlocation.getLatitude())+","+Double.toString(curlocation.getLongitude());
//        Toast.makeText(this,mssg,Toast.LENGTH_SHORT).show();

        cLatLng = new LatLng(curlocation.getLatitude(),curlocation.getLongitude());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(Map.this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        c=1;
        if (mGoogleApiClient!=null)
        {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        c=1;
        if(mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
    }
    public void sendsms()
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("8987516567",null,"hello",null,null);
        Toast.makeText(Map.this,"sent",Toast.LENGTH_SHORT).show();

    }
}
