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

    public LiveData<List<MovieModel>> getMovies() {
        return mRepository.getMovies();
    }

    public LiveData<List<MovieModel>> getNowPlaying() {
        return mRepository.getNowPlaying();
    }

    //calling the trending in view-model
    public void searchMovieApi(String query, int pageNumber) {
        mRepository.searchMovieApi(query, pageNumber);
    }

    //calling the now Playing in view-model
    public void searchMovieApiNowPlaying(int pageNumber) {
        mRepository.searchMovieApiNowPlaying(pageNumber);
    }

    //pagination Support
    public void searchNextPage() {
        mRepository.searchNextPage();
    }

    public LiveData<Boolean> getLoading() {
        return mRepository.getLoading();
    }

}
