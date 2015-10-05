package ru.nitrobubbles.motoplaces.model;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class MockMotoplaces {
    public static ArrayList<Motoplace> getMockMotoplace() {
        ArrayList<Motoplace> motoplaces = new ArrayList<>();
        motoplaces.add(new Motoplace(0).setLatLng(55.653068, 37.506027).setPlaceTypes(PlaceType.FOOD).setTitle("Кафе Пикассо").setSubscription("Скидка 20% мотоциклистам"));
        motoplaces.add(new Motoplace(1).setLatLng(55.829363, 37.647538).setPlaceTypes(PlaceType.SHOP).setTitle("Мистермото").setSubscription("Мотосалон, большой выбор экипировки и техники"));
        motoplaces.add(new Motoplace(2).setLatLng(55.708012, 37.443347).setPlaceTypes(PlaceType.WORKSHOP).setTitle("Магазин мотозапчастей Motorradhof").setSubscription("Ремонт мотоциклов"));
        String gson = new Gson().toJson(motoplaces);
        return motoplaces;
    }
}
