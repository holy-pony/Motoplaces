package ru.nitrobubbles.motoplaces.support;

import ru.nitrobubbles.motoplaces.model.Motoplace;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public interface MapInterface {
    void setCenter(double lat, double lng);
    void setCenterZoom();
    Object addMarker(Motoplace motoplace);
}
