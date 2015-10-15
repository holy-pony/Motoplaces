package ru.nitrobubbles.motoplaces.support;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ru.nitrobubbles.motoplaces.App;
import ru.nitrobubbles.motoplaces.R;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by konstantinaksenov on 02.10.15.
 */
public class GeoSupport {
    public static Observable<String> loadAddress(LatLng latLng) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(App.getInstance(), Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    subscriber.onNext(addresses.get(0).getAddressLine(0));
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onNext(App.getInstance().getString(R.string.unknown_address));
                }
            }
        });
    }

    public static Location latLngToLocation(LatLng latLng){
        Location location = new Location("newloc");
        location.setLongitude(latLng.longitude);
        location.setLatitude(latLng.latitude);
        return location;
    }
}
