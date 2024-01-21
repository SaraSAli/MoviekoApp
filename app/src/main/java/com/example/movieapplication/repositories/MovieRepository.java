package com.example.movieapplication.repositories;

import androidx.lifecycle.LiveData;

import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;
    private int mMovieId;


    public static MovieRepository getInstance() {
        if (instance == null) instance = new MovieRepository();
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }
    public LiveData<List<MovieModel>> getNowPlaying() {
        return movieApiClient.getNowPlaying();
    }

    //calling the trending
    public void searchMovieApi(String query, int pageNumber) {
        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    //calling the Now Playing
    public void searchMovieApiNowPlaying(int pageNumber) {
        movieApiClient.searchMoviesNowPlaying(pageNumber);
    }

    //Pagination Support
    public void searchNextPage() {
        searchMovieApi(mQuery, mPageNumber + 1);
    }

    public  LiveData<Boolean> getLoading(){
        return movieApiClient.movieLoading;
    }
}
