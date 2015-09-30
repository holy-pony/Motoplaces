package ru.nitrobubbles.motoplaces.fragments;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import ru.nitrobubbles.motoplaces.R;
import ru.nitrobubbles.motoplaces.model.MockMotoplaces;
import ru.nitrobubbles.motoplaces.model.Motoplace;
import ru.nitrobubbles.motoplaces.support.SharedPreferencesManager;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class MotoplacesMapFragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnCameraChangeListener{
    private GoogleMap mMap;
    FragmentManager fragmentManager;
    HashMap<Marker, Motoplace> hashMapMarker = new HashMap<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.motoplaces_map_fragment, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map))
                .getMap();
        mMap.setOnMarkerClickListener(this);
        mMap.setOnCameraChangeListener(this);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(location -> {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(SharedPreferencesManager.getInstance().getLastCameraPosition()));
            mMap.setOnMyLocationChangeListener(null);
        });
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(SharedPreferencesManager.getInstance().getLastCameraPosition()));
        setupDots();
    }

    void setupDots(){
        for(Motoplace motoplace : MockMotoplaces.getMockMotoplace()){
           hashMapMarker.put(mMap.addMarker(new MarkerOptions().position(motoplace.getLatLng()).icon(BitmapDescriptorFactory.fromResource(R.drawable.food_marker)).anchor(0.5f,0.5f).title(motoplace.getTitle())), motoplace);
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Motoplace motoplace = hashMapMarker.get(marker);
        Bundle bundle = new Bundle();
        bundle.putSerializable("motoplace", motoplace);
        Fragment fragment = new InfoFooterFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.bottom_container, fragment).addToBackStack(null).commit();
        return true;
    }



    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        SharedPreferencesManager.getInstance().setLastCameraPosition(cameraPosition);
    }

}
