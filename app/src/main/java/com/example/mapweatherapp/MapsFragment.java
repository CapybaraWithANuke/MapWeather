package com.example.mapweatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    SearchView searchView;

    GoogleMap map;
    public static LatLng latLng;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        searchView = rootView.findViewById(R.id.sv_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder;
                    geocoder = new Geocoder(MapsFragment.this.getContext());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (addressList != null && !addressList.isEmpty()) {
                        Address address = addressList.get(0);
                        // ...

                        latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        if (map != null) {
                            map.addMarker(new MarkerOptions().position(latLng).title(location));
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        }
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        mapFragment.getMapAsync(this);

        return rootView;

    }


    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }


}