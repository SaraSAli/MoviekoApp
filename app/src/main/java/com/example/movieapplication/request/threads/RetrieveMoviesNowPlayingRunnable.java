package com.example.movieapplication.request.threads;

import static com.example.movieapplication.request.MovieApiClient.mMoviesNowPlaying;
import static com.example.movieapplication.request.MovieApiClient.nowPlayingLoading;

import android.util.Log;

import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.request.MyService;
import com.example.movieapplication.response.MovieSearchResponse;
import com.example.movieapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RetrieveMoviesNowPlayingRunnable implements Runnable {
    private int pageNumber;
    boolean cancelRequest;

    public RetrieveMoviesNowPlayingRunnable(int pageNumber) {
        nowPlayingLoading.postValue(true);
        this.pageNumber = pageNumber;
        cancelRequest = false;
    }

    @Override
    public void run() {
        try {
            nowPlayingLoading.postValue(true);
            Response response = getMovies(pageNumber).execute();
            if (cancelRequest) {
                nowPlayingLoading.postValue(false);
                return;
            }
            if (response.code() == 200) {
                nowPlayingLoading.postValue(false);
                List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                if (pageNumber == 1) {
                    //sending to live data
                    //post value is for background
                    //set value is for background
                    mMoviesNowPlaying.postValue(list);
                } else {
                    nowPlayingLoading.postValue(false);
                    List<MovieModel> currentMovies = mMoviesNowPlaying.getValue();
                    currentMovies.addAll(list);
                    mMoviesNowPlaying.postValue(currentMovies);

                }
            } else {
                nowPlayingLoading.postValue(false);
                String error = response.errorBody().string();
                Log.v("TAG", "Error " + error);
                mMoviesNowPlaying.postValue(null);
            }
        } catch (IOException e) {
            nowPlayingLoading.postValue(false);
            e.printStackTrace();
        }
    }

    //Now Playing query
    private Call<MovieSearchResponse> getMovies(int pageNumber) {
        return MyService.getMovieApi().getNowPlaying(
                Credentials.MY_API_KEY,
                pageNumber);
    }

    private void cancelRequest() {
        Log.v("TAG", "Cancelling the Request");
        cancelRequest = true;
    }
}
