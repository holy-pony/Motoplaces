package ru.nitrobubbles.motoplaces.support;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import ru.nitrobubbles.motoplaces.model.Motoplace;

/**
 * Created by konstantinaksenov on 02.10.15.
 */
public class MotoplaceMapRender extends DefaultClusterRenderer<Motoplace> {

    public MotoplaceMapRender(Activity context, GoogleMap map, ClusterManager<Motoplace> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(Motoplace person, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromResource(person.getPlaceTypes()[0].getIconId())).anchor(0.5f, 0.5f);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<Motoplace> cluster) {
        return cluster.getSize() > 1;
    }


}
