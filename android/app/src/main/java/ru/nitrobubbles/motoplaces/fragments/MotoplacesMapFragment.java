package ru.nitrobubbles.motoplaces.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nitrobubbles.motoplaces.R;
import ru.nitrobubbles.motoplaces.model.MockMotoplaces;
import ru.nitrobubbles.motoplaces.model.Motoplace;
import ru.nitrobubbles.motoplaces.support.MotoplaceMapRender;
import ru.nitrobubbles.motoplaces.support.SharedPreferencesManager;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class MotoplacesMapFragment extends Fragment implements ClusterManager.OnClusterItemClickListener<Motoplace>, ClusterManager.OnClusterClickListener<Motoplace>, OnCameraChangeListener {
    private GoogleMap mMap;
    FragmentManager fragmentManager;
    private ClusterManager<Motoplace> clusterManager;
    private Queue<CameraPosition> cameraPositionStore = Collections.asLifoQueue(new ArrayDeque<>());
    @Bind(R.id.bottom_container)
    FrameLayout bottomContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.motoplaces_map_fragment, null);
        ButterKnife.bind(this, v);
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
        clusterManager.setOnClusterClickListener(this);
        MotoplaceMapRender motoplaceMapRender = new MotoplaceMapRender(getActivity(), mMap, clusterManager);
        clusterManager.setRenderer(motoplaceMapRender);

        mMap.setOnMarkerClickListener(clusterManager);
        mMap.setOnCameraChangeListener(this);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnMyLocationChangeListener(location -> {
            LatLng myLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            if(SharedPreferencesManager.getInstance().getLastCameraPosition().zoom == 0f)
                  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 17));
            SharedPreferencesManager.getInstance().setMyPosition(myLatLng);
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
        clusterManager.cluster();
    }


    @Override
    public boolean onClusterItemClick(Motoplace motoplace) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("motoplace", motoplace);
        Fragment fragment = new InfoFooterFragment();
        fragment.setArguments(bundle);
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().replace(R.id.bottom_container, fragment).addToBackStack(null).commit();
        bottomContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cameraPositionStore.add(mMap.getCameraPosition());
                int height = bottomContainer.getHeight();
                mMap.setPadding(0, 0, 0, height);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(motoplace.getPosition(), 17));
                bottomContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mMap.setPadding(0, 0, 0, 0);
            }
        });

        return false;
    }

    @Override
    public boolean onClusterClick(Cluster<Motoplace> cluster) {
        cameraPositionStore.add(mMap.getCameraPosition());
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Motoplace mcluster : cluster.getItems()) {
            builder.include(mcluster.getPosition());
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
        mMap.animateCamera(cu);
        return true;
    }

    public boolean zoomStoreIsEmpty(){
        if(cameraPositionStore.isEmpty())
            return true;
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPositionStore.poll()));
        return false;
    }
}
