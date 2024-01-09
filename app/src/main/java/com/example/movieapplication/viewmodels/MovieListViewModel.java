package com.example.movieapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository mRepository;

    public MovieListViewModel() {
        mRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovieModel() {
        return mRepository.getMovieMovies();
    }

    public void searchMovieApi(String query, int pageNumber) {
        mRepository.searchMovieApi(query, pageNumber);
    }
}
