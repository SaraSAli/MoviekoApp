package com.example.movieapplication.request.threads;

import static com.example.movieapplication.request.MovieApiClient.mMovies;
import static com.example.movieapplication.request.MovieApiClient.movieLoading;

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

public class RetrieveMoviesRunnable implements Runnable {

    private String query;
    private int pageNumber;
    boolean cancelRequest;

    public RetrieveMoviesRunnable(String query, int pageNumber) {
        movieLoading.postValue(true);
        this.query = query;
        this.pageNumber = pageNumber;
        cancelRequest = false;
    }

    @Override
    public void run() {
        try {
            movieLoading.postValue(true);
            Response response = getMovies(query, pageNumber).execute();
            if (cancelRequest) {
                return;
            }
            if (response.code() == 200) {
                movieLoading.postValue(false);
                List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                if (pageNumber == 1) {
                    //sending to live data
                    //post value is for background
                    //set value is for background
                    mMovies.postValue(list);
                } else {
                    List<MovieModel> currentMovies = mMovies.getValue();
                    currentMovies.addAll(list);
                    mMovies.postValue(currentMovies);

                }
            } else {
                movieLoading.postValue(false);
                String error = response.errorBody().string();
                Log.v("TAG", "Error " + error);
                mMovies.postValue(null);
            }
        } catch (IOException e) {
            movieLoading.postValue(false);
            e.printStackTrace();
        }
    }

    //Trending Query
    private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
        return MyService.getMovieApi().getTrending(
                Credentials.MY_API_KEY,
                pageNumber);
    }

    private void cancelRequest() {
        Log.v("TAG", "Cancelling the Request");
        cancelRequest = true;
    }
}
