package ru.nitrobubbles.motoplaces.model;


import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class Motoplace implements Serializable{
    private int id;
    private double lat, lng;
    private String title, subscription, address, site, phone;
    private PlaceType[] placeTypes;

    public Motoplace(int id) {
        this.id = id;
    }

    public Motoplace setLatLng(double lat, double lng){
        this.lat = lat;
        this.lng = lng;
        return this;
    }

    public Motoplace setLatLng(LatLng latLng){
        lat = latLng.latitude;
        lng = latLng.longitude;
        return this;
    }

    public Motoplace setTitle(String title) {
        this.title = title;
        return this;
    }

    public Motoplace setSubscription(String subscription) {
        this.subscription = subscription;
        return this;
    }

    public Motoplace setAddress(String address) {
        this.address = address;
        return this;
    }

    public Motoplace setSite(String site) {
        this.site = site;
        return this;
    }

    public Motoplace setPlaceTypes(PlaceType... placeTypes) {
        this.placeTypes = placeTypes;
        return this;
    }

    public int getId() {
        return id;
    }

    public LatLng getLatLng(){
        return new LatLng(lat, lng);
    }

    public String getTitle() {
        return title;
    }

    public String getSubscription() {
        return subscription;
    }

    public String getAddress() {
        return address;
    }

    public String getSite() {
        return site;
    }

    public PlaceType[] getPlaceTypes() {
        return placeTypes;
    }
}
