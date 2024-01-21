package com.example.movieapplication.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapplication.AppExecutors;
import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.request.threads.RetrieveMoviesNowPlayingRunnable;
import com.example.movieapplication.request.threads.RetrieveMoviesRunnable;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MovieApiClient {
    public static MutableLiveData<List<MovieModel>> mMovies;
    public static MutableLiveData<List<MovieModel>> mMoviesNowPlaying;
    private static MovieApiClient instance;

    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    private RetrieveMoviesNowPlayingRunnable retrieveMoviesNowPlayingRunnable;

    public static MutableLiveData<Boolean> movieLoading;
    public static MutableLiveData<Boolean> nowPlayingLoading;

    public static MovieApiClient getInstance() {
        if (instance == null) instance = new MovieApiClient();
        return instance;
    }

    public MovieApiClient() {
        mMovies = new MutableLiveData<>();
        mMoviesNowPlaying = new MutableLiveData<>();

        movieLoading = new MutableLiveData<>(true);
        nowPlayingLoading = new MutableLiveData<>(true);
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    public LiveData<List<MovieModel>> getNowPlaying() {
        return mMoviesNowPlaying;
    }

    public void searchMoviesApi(String query, int pageNumber) {
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future mHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                mHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    public void searchMoviesNowPlaying(int pageNumber) {

        if (retrieveMoviesNowPlayingRunnable != null) {
            retrieveMoviesNowPlayingRunnable = null;
        }

        retrieveMoviesNowPlayingRunnable = new RetrieveMoviesNowPlayingRunnable(pageNumber);

        final Future mMoviesNowPlayingHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesNowPlayingRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                mMoviesNowPlayingHandler.cancel(true);

            }
        }, 3000, TimeUnit.MILLISECONDS);
    }
}
