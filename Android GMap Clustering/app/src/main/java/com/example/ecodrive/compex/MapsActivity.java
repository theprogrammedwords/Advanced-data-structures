package com.example.ecodrive.compex;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static String TAG = MapsActivity.class.getName();
    private List<LatLng> latLngArrayList;
    private int node_count=0;
    private Button contraction;
    private Button expansion;
    private Button clear;
    Polyline polyline;
    private LatLng marker1;
    private LatLng marker2;
    private int markercount=0;
    private ClusterManager<MyItem> mClusterManager;
    private float currentZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
	//managing latitude longitude points in an array list 
        latLngArrayList = new ArrayList<>();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
	//setting zoom level as per the user input key event
        switch (keyCode) {
            case KeyEvent.KEYCODE_3:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                Toast.makeText(getApplicationContext(),"Zoom level: "+mMap.getCameraPosition().zoom,Toast.LENGTH_SHORT).show();
                break;
            case KeyEvent.KEYCODE_1:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
                Toast.makeText(getApplicationContext(),"Zoom level: "+mMap.getCameraPosition().zoom,Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Camera view initialized at Nagpur, Maharashtra
        LatLng nagpur = new LatLng(21.1458, 79.0882);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nagpur));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        currentZoom=10;

        setUpClusterer(mMap);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
               latLngArrayList.add(point);
                mMap.addMarker(new MarkerOptions().position(point).title(""+(++node_count)));
            }
        });

        //noinspection deprecation
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if(cameraPosition.zoom!=currentZoom) {
                    if(cameraPosition.zoom>currentZoom)
                    Toast.makeText(getApplicationContext(), "Zoom level: " + mMap.getCameraPosition().zoom+"\n Expanding ...", Toast.LENGTH_SHORT).show();
                    currentZoom=cameraPosition.zoom;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Zoom level: " + mMap.getCameraPosition().zoom+"\n Compressing ...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

            return false;
            }
        });
    }
    private void setUpClusterer(GoogleMap mMap) {
        // Position the map.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.1458,79.0882), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyItem>(this, mMap);
        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        // Add cluster items (markers) to the cluster manager.
//        addItems(new LatLng(21.1458,79.0882));
    }
    private void addItems(LatLng point) {

        // Add ten cluster items in close proximity, for purposes of this example.
        MyItem offsetItem = new MyItem(point.latitude, point.longitude);
        mClusterManager.addItem(offsetItem);
    }

    public void startClustering(View view) {
        List<MyItem> myItemList = new ArrayList<>();
        for(LatLng p: latLngArrayList)
        {
            myItemList.add(new MyItem(p.latitude,p.longitude));
        }
        mClusterManager.addItems(myItemList);
    }

    public void clearMap(View view) {
        if(mMap!=null)
            mMap.clear();
    }
}
