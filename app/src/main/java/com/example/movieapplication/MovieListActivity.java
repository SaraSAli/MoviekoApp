package com.example.movieapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.request.MyService;
import com.example.movieapplication.response.MovieSearchResponse;
import com.example.movieapplication.utils.Credentials;
import com.example.movieapplication.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListActivity extends AppCompatActivity {

    //private ActivityMovieListBinding binding;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressCircular.setVisibility(View.INVISIBLE);*/

        setContentView(R.layout.activity_movie_list);
        btn = findViewById(R.id.test_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRetrofitResponseAccordingToId();
            }
        });


    }

    private void getRetrofitResponse() {
        MovieApi movieApi = MyService.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(
                Credentials.MY_API_KEY,
                "Action",
                1
        );

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    Log.v("Tag", "the response " + response.body().toString());

                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    for (MovieModel movie :
                            movies) {
                        Log.v("Tag", "the release date " + movie.getRelease_date());
                    }
                }
                else{
                    try {
                        Log.v("Tag", "Error "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }

    private void getRetrofitResponseAccordingToId(){
        MovieApi movieApi = MyService.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(
                550,
                Credentials.MY_API_KEY
        );

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code() == 200){
                    MovieModel movie = response.body();
                    Log.v("Tag", "The response "+movie.getTitle());
                }
                else{
                    try {
                        Log.v("Tag", "Error "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }
}