package ru.nitrobubbles.motoplaces.support;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.tileprovider.MapTileLayerBase;
import com.mapbox.mapboxsdk.views.MapView;

import ru.nitrobubbles.motoplaces.model.Motoplace;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class MyMapBox extends MapView implements MapInterface{
    protected MyMapBox(Context aContext, int tileSizePixels, MapTileLayerBase tileProvider, Handler tileRequestCompleteHandler, AttributeSet attrs) {
        super(aContext, tileSizePixels, tileProvider, tileRequestCompleteHandler, attrs);
    }

    public MyMapBox(Context aContext) {
        super(aContext);
    }

    public MyMapBox(Context aContext, AttributeSet attrs) {
        super(aContext, attrs);
    }

    protected MyMapBox(Context aContext, int tileSizePixels, MapTileLayerBase aTileProvider) {
        super(aContext, tileSizePixels, aTileProvider);
    }

    @Override
    public void setCenter(double lat, double lng) {
        setCenter(new LatLng(lat, lng));
    }

    @Override
    public void setCenterZoom() {

    }

    @Override
    public Marker addMarker(Motoplace motoplace) {
       return addMarker(new Marker(motoplace.getTitle(), motoplace.getSubscription(), motoplace.getLatLng()));
    }
}
