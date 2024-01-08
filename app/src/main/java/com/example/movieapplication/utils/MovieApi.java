package com.example.movieapplication.utils;

import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.response.MovieResponse;
import com.example.movieapplication.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

    //http://api.themoviedb.org/3/movie/550?api_key=b43b43778791374756a89f02dba27901
    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key
    );
}
