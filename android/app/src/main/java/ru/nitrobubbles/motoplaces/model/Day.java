package ru.nitrobubbles.motoplaces.model;

import ru.nitrobubbles.motoplaces.R;

/**
 * Created by konstantinaksenov on 13.10.15.
 */
public enum  Day {
    MONDAY(R.string.monday),
    TUESADY(R.string.tuesday),
    WENSDAY(R.string.wensday),
    THURSTDAY(R.string.thursday),
    FRIDAY(R.string.friday),
    SUNDAY(R.string.sunday),
    SATURDAY(R.string.saturday);

    int dayString;

    Day(int dayString) {
        this.dayString = dayString;
    }

    public int getDayString() {
        return dayString;
    }
}
