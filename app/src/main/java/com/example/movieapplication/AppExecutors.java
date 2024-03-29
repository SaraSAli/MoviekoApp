package com.example.movieapplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * To allow services in the background (like retrofit calls without showing to the user
 **/
public class AppExecutors {
    //singleton pattern
    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if (instance == null) {
            instance = new AppExecutors();

        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO() {
        return mNetworkIO;
    }
}
