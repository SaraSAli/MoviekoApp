package com.example.movieapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapplication.model.MovieModel;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> mMovies = new MutableLiveData<>();

    public MovieListViewModel() {

    }

    public LiveData<List<MovieModel>> getMovieModel(){
        return  mMovies;
    }
}
