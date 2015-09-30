package ru.nitrobubbles.motoplaces.model;

import com.google.gson.annotations.SerializedName;

import ru.nitrobubbles.motoplaces.App;
import ru.nitrobubbles.motoplaces.R;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public enum PlaceType {
    @SerializedName("shop")
    SHOP(App.getInstance().getString(R.string.shop)),
    @SerializedName("workshop")
    WORKSHOP(App.getInstance().getString(R.string.workshop)),
    @SerializedName("food")
    FOOD(App.getInstance().getString(R.string.food));

    PlaceType(String title) {
        this.title = title;
    }

    String title;


}
