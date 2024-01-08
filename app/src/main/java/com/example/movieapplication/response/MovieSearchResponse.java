package com.example.movieapplication.response;

import com.example.movieapplication.model.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("total_results")
    @Expose
    private int moviesTotalCount;

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies;

    public int getMoviesTotalCount(){
        return moviesTotalCount;
    }

    public List<MovieModel> getMovies(){
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "moviesTotalCount=" + moviesTotalCount +
                ", movies=" + movies +
                '}';
    }
}
