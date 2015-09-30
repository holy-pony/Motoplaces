package ru.nitrobubbles.motoplaces.model;

import java.util.ArrayList;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class MockMotoplaces {
    public static ArrayList<Motoplace> getMockMotoplace(){
        ArrayList<Motoplace> motoplaces = new ArrayList<>();
        motoplaces.add(new Motoplace(0).setLatLng(55.653068, 37.506027).setAddress("Миклухо-Маклая ул., 15").setPlaceTypes(PlaceType.FOOD).setTitle("Кафе Пикассо").setSubscription("Скидка 20% мотоциклистам"));
        return motoplaces;
    }
}
