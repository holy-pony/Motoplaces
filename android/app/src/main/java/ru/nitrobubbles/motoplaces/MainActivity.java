package ru.nitrobubbles.motoplaces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.views.MapView;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nitrobubbles.motoplaces.model.MockMotoplaces;
import ru.nitrobubbles.motoplaces.model.Motoplace;
import ru.nitrobubbles.motoplaces.support.MyMapBox;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.mapview)
    MyMapBox mapBox;
    Drawer drawer;
    HashMap<Marker, Motoplace> motoplaceHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);


        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withStatusBarColor(getResources().getColor(R.color.primaryDark))
/*                .withHeader(R.layout.drawer_header)*/
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.support).withIcon(GoogleMaterial.Icon.gmd_phone)
                )
                .build();
        mapBox.setCenter(55.5, 37.5);
        initMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initMap() {
        for (Motoplace motoplace : MockMotoplaces.getMockMotoplace()) {
            motoplaceHashMap.put(mapBox.addMarker(motoplace), motoplace);
        }
    }


}
