package ru.nitrobubbles.motoplaces.support;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import ru.nitrobubbles.motoplaces.App;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class SharedPreferencesManager {
    private static SharedPreferencesManager ourInstance = new SharedPreferencesManager();
    SharedPreferences sharedPreferences;

    public static SharedPreferencesManager getInstance() {
        return ourInstance;
    }

    private SharedPreferencesManager() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    public void setLastCameraPosition(CameraPosition cameraPosition) {
        sharedPreferences.edit()
                .putFloat("lat", (float) cameraPosition.target.latitude)
                .putFloat("lng", (float) cameraPosition.target.longitude)
                .putFloat("zoom", cameraPosition.zoom)
                .commit();
    }

    public CameraPosition getLastCameraPosition() {
        double lat = sharedPreferences.getFloat("lat", 0f);
        double lng = sharedPreferences.getFloat("lng", 0f);
        float zoom = sharedPreferences.getFloat("zoom", 0f);
        CameraPosition cameraPosition = new CameraPosition(new LatLng(lat, lng), zoom, 0, 0);
        return cameraPosition;
    }
}
