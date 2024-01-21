package com.example.movieapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.adapter.MovieRecyclerViewAdapter;
import com.example.movieapplication.adapter.NowPlayingMoviesAdapter;
import com.example.movieapplication.databinding.ActivityMovieListBinding;
import com.example.movieapplication.databinding.LoadingLayoutBinding;
import com.example.movieapplication.model.MovieModel;
import com.example.movieapplication.request.MyService;
import com.example.movieapplication.response.MovieSearchResponse;
import com.example.movieapplication.utils.Credentials;
import com.example.movieapplication.utils.MovieApi;
import com.example.movieapplication.viewmodels.MovieListViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListActivity extends AppCompatActivity {

    private ActivityMovieListBinding binding;
    private LoadingLayoutBinding loadingBinding;

    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    private NowPlayingMoviesAdapter nowPlayingMoviesAdapter;
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);


        initRecyclerView();
        initViewPager();

        //calling the observers
        observeMovieTrendingChange();
        observeMovieNowPlayingChange();

        //api Calls
        searchMovieApi("", 1);
        searchMovieApiNowPlaying(1);

        /*movieListViewModel.getLoading().observe(this, loading -> {
            if (loading) {
                loadingBinding.loadingAnimation.setVisibility(View.VISIBLE);
            } else {
                loadingBinding.loadingAnimation.setVisibility(View.GONE);
            }
        });*/

    }

    //observing changes
    private void observeMovieTrendingChange() {
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observe any data change
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        //get the data in log
                        Log.d("TAG", "observeMovieTrendingChange onChanged() called with: movieModels = [" + movieModel.getTitle() + "]");
                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    private void observeMovieNowPlayingChange() {
        movieListViewModel.getNowPlaying().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observe any data change
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        //get the data in log
                        Log.d("TAG", "observeMovieNowPlayingChange onChanged() called with: movieModels = [" + movieModel.getTitle() + "]");
                        nowPlayingMoviesAdapter.setNowPlayingMovies(movieModels);
                    }
                }
            }
        });
    }

    //calling the trending in main-activity
    private void searchMovieApi(String query, int pageNumber) {
        movieListViewModel.searchMovieApi(query, pageNumber);
    }

    //calling the now-playing in main activity
    private void searchMovieApiNowPlaying(int pageNumber) {
        movieListViewModel.searchMovieApiNowPlaying(pageNumber);
    }

    private void initRecyclerView() {
        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.moviesRecyclerView.setLayoutManager(layoutManager);
        binding.moviesRecyclerView.setAdapter(movieRecyclerViewAdapter);

        //Pagination support
        binding.moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!binding.moviesRecyclerView.canScrollVertically(1)) {
                    //here we need to get another page of data
                    movieListViewModel.searchNextPage();
                }
            }
        });

    }

    private void initViewPager(){
        nowPlayingMoviesAdapter = new NowPlayingMoviesAdapter(this);
        binding.moviesViewPager.setAdapter(nowPlayingMoviesAdapter);

        //indicator
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.viewPagerIndicator
                , binding.moviesViewPager, true, true, (tab, position) -> {
            //something to do that i am not sure of
        });
        tabLayoutMediator.attach();
    }

/*    private void getRetrofitResponse() {
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
    }*/
}