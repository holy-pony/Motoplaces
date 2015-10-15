package ru.nitrobubbles.motoplaces.model;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class MockMotoplaces {
    public static ArrayList<Motoplace> getMockMotoplace() {
        ArrayList<Motoplace> motoplaces = new ArrayList<>();
        motoplaces.add(new Motoplace(0)
                .setLatLng(55.653068, 37.506027)
                .setPlaceTypes(PlaceType.FOOD)
                .setTitle("Кафе Пикассо")
                .setSubscription("Скидка 20% мотоциклистам")
                .setWorkedDays(
                        Day.MONDAY,
                        Day.TUESADY,
                        Day.WENSDAY,
                        Day.THURSTDAY,
                        Day.FRIDAY,
                        Day.SATURDAY,
                        Day.SUNDAY));


        motoplaces.add(new Motoplace(1).setLatLng(55.829363, 37.647538).setPlaceTypes(PlaceType.SHOP).setTitle("Мистермото").setAddress("ул. Сергея Эзейнштерна 1").setSubscription("Мотосалон, большой выбор экипировки и техники"));
        motoplaces.add(new Motoplace(2).setLatLng(55.708012, 37.443347).setPlaceTypes(PlaceType.WORKSHOP).setTitle("Магазин мотозапчастей Motorradhof").setSubscription("Ремонт мотоциклов"));
        motoplaces.add(new Motoplace(3).setLatLng(55.709532, 37.542038).setPlaceTypes(PlaceType.MEETING).setTitle("Смотра").setSubscription("Место встречи"));
        String gson = new Gson().toJson(motoplaces);
        return motoplaces;
    }
}
