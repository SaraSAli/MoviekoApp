package com.example.movieapplication.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapplication.AppExecutors;
import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.request.threads.RetrieveMoviesRunnable;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MovieApiClient {
    public static MutableLiveData<List<MovieModel>> mMovies;
    private static MovieApiClient instance;

    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static MovieApiClient getInstance() {
        if(instance == null) instance = new MovieApiClient();
        return instance;
    }

    public MovieApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return  mMovies;
    }

    public void searchMoviesApi(String query, int pageNumber){
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future mHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancel retrofit calls
                mHandler.cancel(true);
            }
        },5000, TimeUnit.MILLISECONDS);
    }
}
