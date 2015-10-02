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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.rey.material.widget.FloatingActionButton;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.nitrobubbles.motoplaces.R;
import ru.nitrobubbles.motoplaces.support.GeoSupport;
import ru.nitrobubbles.motoplaces.support.SharedPreferencesManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class AddNewPlaceFragment extends Fragment implements GoogleMap.OnCameraChangeListener {

    @Bind(R.id.next_step)
    FloatingActionButton nextButton;
    private String address;

    private GoogleMap mMap;
    private FragmentManager fragmentManager;
    private CompositeSubscription compositeSubscription;
    private PublishSubject<CameraPosition> cameraPositionPublishSubject = PublishSubject.create();
    private Action1<CameraPosition> loadAddressAction = cameraPosition -> loadAddress(cameraPosition.target);

    @Override
    public void onStart() {
        super.onStart();
        compositeSubscription = new CompositeSubscription();
        compositeSubscription
                .add(cameraPositionPublishSubject
                        .debounce(2500, TimeUnit.MILLISECONDS)
                        .subscribe(loadAddressAction));
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeSubscription.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_place_fragment, null);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getChildFragmentManager();
        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map))
                .getMap();
        mMap.setOnCameraChangeListener(this);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(SharedPreferencesManager.getInstance().getLastCameraPosition()));
    }


    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        hideNextButton();
        cameraPositionPublishSubject.onNext(cameraPosition);
    }


    private void loadAddress(LatLng target) {
        GeoSupport.loadAddress(target)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(address -> {
                    AddNewPlaceFragment.this.address = address;
                    showNextButton();
                });
    }


    private void hideNextButton() {
        nextButton.setVisibility(View.GONE);
    }

    private void showNextButton() {
        nextButton.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.next_step)
    void showSubInfoLayout() {
        fragmentManager.popBackStack();
        Bundle bundle = new Bundle();
        bundle.putString("address", address);
        Fragment fragment = new AddNewPlaceSubInfoFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.bottom_container, fragment).addToBackStack(null).commit();

    }
}
