package com.arshad.mindvalley.global;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by root on 30/1/17.
 */
public class AppController extends Application {

    private static AppController instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "semi-bold.ttf");
        instance = this;
    }

    public static synchronized AppController getInstance() {
        return instance;
    }

}
