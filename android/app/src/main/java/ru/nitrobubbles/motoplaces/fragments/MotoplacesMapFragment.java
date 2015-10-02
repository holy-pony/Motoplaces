package ru.nitrobubbles.motoplaces.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import ru.nitrobubbles.motoplaces.R;
import ru.nitrobubbles.motoplaces.model.MockMotoplaces;
import ru.nitrobubbles.motoplaces.model.Motoplace;
import ru.nitrobubbles.motoplaces.support.MotoplaceMapRender;
import ru.nitrobubbles.motoplaces.support.SharedPreferencesManager;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class MotoplacesMapFragment extends Fragment implements ClusterManager.OnClusterItemClickListener<Motoplace>, OnCameraChangeListener {
    private GoogleMap mMap;
    FragmentManager fragmentManager;
    private ClusterManager<Motoplace> clusterManager;

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

        clusterManager = new ClusterManager<>(getActivity().getApplicationContext(), mMap);

        clusterManager.setOnClusterItemClickListener(this);
        MotoplaceMapRender motoplaceMapRender = new MotoplaceMapRender(getActivity(), mMap, clusterManager);
        clusterManager.setRenderer(motoplaceMapRender);

        mMap.setOnMarkerClickListener(clusterManager);
        mMap.setOnCameraChangeListener(clusterManager);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(location -> {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(SharedPreferencesManager.getInstance().getLastCameraPosition()));
            mMap.setOnMyLocationChangeListener(null);
        });
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(SharedPreferencesManager.getInstance().getLastCameraPosition()));
        setupDots();
    }

    void setupDots() {
        for (Motoplace motoplace : MockMotoplaces.getMockMotoplace()) {
            clusterManager.addItem(motoplace);
        }
        clusterManager.cluster();
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        SharedPreferencesManager.getInstance().setLastCameraPosition(cameraPosition);
    }


    @Override
    public boolean onClusterItemClick(Motoplace motoplace) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("motoplace", motoplace);
        Fragment fragment = new InfoFooterFragment();
        fragment.setArguments(bundle);
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.bottom_container, fragment).addToBackStack(null).commit();
        return false;
    }
}
