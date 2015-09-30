package ru.nitrobubbles.motoplaces;

import android.app.Application;
import android.content.Context;

/**
 * Created by konstantinaksenov on 30.09.15.
 */
public class App extends Application {
    private static Context instance;

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
