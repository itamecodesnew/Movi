package com.itamecodes.moviepot;

import android.app.Application;
import android.util.Log;

import timber.log.Timber;

/**
 * Created by ananya on 3/22/14.
 */
public class MoviApplication extends Application {
    public static final boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if(MoviApplication.DEBUG){
            Log.v("testing","123");
            Timber.plant(new Timber.DebugTree());
        }else{
            Log.v("testing","456");
        }

    }
}
