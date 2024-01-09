package com.example.movieapplication.repositories;

import androidx.lifecycle.LiveData;

import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;


    public static MovieRepository getInstance() {
        if (instance == null) instance = new MovieRepository();
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovieMovies() {
        return movieApiClient.getMovies();
    }

    public void searchMovieApi(String query, int pageNumber) {
        movieApiClient.searchMoviesApi(query, pageNumber);
    }
}
