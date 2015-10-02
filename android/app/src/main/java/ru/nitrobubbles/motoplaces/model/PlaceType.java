package ru.nitrobubbles.motoplaces.model;

import com.google.gson.annotations.SerializedName;

import ru.nitrobubbles.motoplaces.App;
import ru.nitrobubbles.motoplaces.R;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public enum PlaceType {
    @SerializedName("shop")
    SHOP(App.getInstance().getString(R.string.shop), R.drawable.shop_marker),
    @SerializedName("workshop")
    WORKSHOP(App.getInstance().getString(R.string.workshop), R.drawable.workshop_marker),
    @SerializedName("food")
    FOOD(App.getInstance().getString(R.string.food), R.drawable.food_marker);

    PlaceType(String title, int iconId) {
        this.title = title;
        this.iconId = iconId;
    }

    String title;
    int iconId;

    public String getTitle() {
        return title;
    }

    public int getIconId() {
        return iconId;
    }
}
